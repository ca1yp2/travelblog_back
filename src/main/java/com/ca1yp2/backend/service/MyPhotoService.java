package com.ca1yp2.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ca1yp2.backend.entity.MyPhoto;
import com.ca1yp2.backend.entity.PhotoCategory;
import com.ca1yp2.backend.repository.MyPhotoRepository;
import com.ca1yp2.backend.repository.PhotoCategoryRepository;

@Service
public class MyPhotoService {

    private final MyPhotoRepository photoRepository;
    private final PhotoCategoryRepository photoCategoryRepository;

    public MyPhotoService(
            MyPhotoRepository photoRepository,
            PhotoCategoryRepository photoCategoryRepository) {
        this.photoRepository = photoRepository;
        this.photoCategoryRepository = photoCategoryRepository;
    }

    // 전체보기
    public List<MyPhoto> getAllPhotos() {
        return photoRepository.findAll();
    }

    // 카테고리별 보기
    public List<MyPhoto> getPhotosByCategory(Integer categoryId) {
        return photoRepository.findByCategoryId(categoryId);
    }

    // 카테고리 목록 보기
    public List<PhotoCategory> getAllPhotoCategories() {
        return photoCategoryRepository.findAll();
    }
}

