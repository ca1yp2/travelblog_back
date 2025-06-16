package com.ca1yp2.backend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    private final Path coverPath = Paths.get("src/main/resources/static/upload/travel/cover");
    private final Path contentPath = Paths.get("src/main/resources/static/upload/travel/contents");

    public String saveMainImage(MultipartFile file){

        return saveFile(file, coverPath);
    };

    public String saveContentsImage(MultipartFile file){
        return saveFile(file, contentPath);
    }

    private String saveFile(MultipartFile file, Path path){
        try{
            if(!Files.exists(path)) Files.createDirectories(path);
            String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
            String uuid = UUID.randomUUID().toString() + "." + ext;
            Files.copy(file.getInputStream(), path.resolve(uuid));
            return uuid;
        }catch(IOException e){
            throw new RuntimeException("파일 저장 실패", e);
        }
    }

    public String getExt(String filename){
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}
