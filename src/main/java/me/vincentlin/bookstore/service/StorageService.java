package me.vincentlin.bookstore.service;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface StorageService {

    GridFSFile store(MultipartFile file);

    List<GridFSDBFile> loadAll();

    Resource load(String filename);

    void delete(String filename);

    void deleteAll();

}
