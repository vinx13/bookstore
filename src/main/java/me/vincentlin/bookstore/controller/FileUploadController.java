package me.vincentlin.bookstore.controller;

import com.mongodb.gridfs.GridFSFile;
import me.vincentlin.bookstore.common.StorageFileNotFoundException;
import me.vincentlin.bookstore.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
public class FileUploadController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {

        Resource file = storageService.load(filename);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @PostMapping("/files")
    public ResponseEntity<?> postFile(@RequestParam("file") MultipartFile file) {

        GridFSFile savedFile = storageService.store(file);
        Map<String, Object> map = new HashMap<>();
        map.put("link", savedFile.getFilename());
        return ResponseEntity.ok().body(map);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
