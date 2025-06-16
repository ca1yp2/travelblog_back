package com.ca1yp2.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ca1yp2.backend.entity.MyPhoto;
import com.ca1yp2.backend.service.MyPhotoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/blog")
public class PhotoController {

    private final MyPhotoService photoService;

    @GetMapping("/photos/list") // 전체보기
    public List<MyPhoto> getAllPhotos() {
        return photoService.getAllPhotos();
    }

    @GetMapping("/photos/category/{categoryId}")
    public List<MyPhoto> getPhotosByCategory(@PathVariable Integer categoryId) {
        return photoService.getPhotosByCategory(categoryId);
    }

}
