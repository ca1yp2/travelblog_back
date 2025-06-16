package com.ca1yp2.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ca1yp2.backend.entity.PostImg;

public interface PostImgRepository extends JpaRepository<PostImg, Long> {
    List<PostImg> findByPostid(Long postid);
}
