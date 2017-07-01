package me.vincentlin.bookstore.service.impl;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import me.vincentlin.bookstore.common.StorageException;
import me.vincentlin.bookstore.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    GridFsOperations operations;

    @Override
    public GridFSFile store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            String ext = getFileExtension(file.getOriginalFilename());
            String filename = UUID.randomUUID().toString() + '.' + ext;
            GridFSFile saved = operations.store(file.getInputStream(), filename);
            return saved;
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public List<GridFSDBFile> loadAll() {
        List<GridFSDBFile> files = operations.find(new Query());
        return files;
    }

    @Override
    public GridFsResource load(String filename) {
        return operations.getResource(filename);
    }

    @Override
    public void delete(String filename){
        Query query = new Query();
        Criteria criteria = Criteria.where("filename").is(filename);
        query.addCriteria(criteria);
        operations.delete(query);
    }

    @Override
    public void deleteAll() {
        operations.delete(new Query());
    }

    private String getFileExtension(String filename) {
        try {
            return filename.substring(filename.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }
}
