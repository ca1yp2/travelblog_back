package com.ca1yp2.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ca1yp2.backend.entity.MyPhoto;

public interface MyPhotoRepository extends JpaRepository<MyPhoto, Long> {
    List<MyPhoto> findByCategoryId(Integer categoryId);
}
