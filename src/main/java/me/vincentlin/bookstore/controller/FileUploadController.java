package me.vincentlin.bookstore.controller;

import com.mongodb.gridfs.GridFSFile;
import me.vincentlin.bookstore.StorageFileNotFoundException;
import me.vincentlin.bookstore.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/files")
    public String listUploadedFiles(Model model) throws IOException {
/*
        model.addAttribute("files", storageService
                .loadAll()
                .toArray()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));
*/
        model.addAttribute("files",
                storageService.loadAll()
                        .stream()
                        .map(file ->
                                MvcUriComponentsBuilder
                                        .fromMethodName(FileUploadController.class, "serveFile", file.getFilename())
                                        .build().toString())
                        .collect(Collectors.toList()));

        return "uploadForm";
    }

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
