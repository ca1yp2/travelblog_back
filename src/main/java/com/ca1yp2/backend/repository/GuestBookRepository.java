package com.ca1yp2.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ca1yp2.backend.entity.GuestBook;

public interface GuestBookRepository extends JpaRepository<GuestBook, Long> {
    List<GuestBook> findByPostIdOrderByIdDesc(Long postId);
}
