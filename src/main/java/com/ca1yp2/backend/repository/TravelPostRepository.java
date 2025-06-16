package com.ca1yp2.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ca1yp2.backend.entity.TravelPost;

@Repository
public interface TravelPostRepository extends JpaRepository<TravelPost, Long> {
    Page<TravelPost> findByNation(String nation, Pageable pageable);

    @Query("SELECT t FROM TravelPost t WHERE " +
            "LOWER(t.title) LIKE %:keyword% OR " +
            "LOWER(t.subtitle) LIKE %:keyword% OR " +
            "LOWER(t.nation) LIKE %:keyword%")
    Page<TravelPost> findByKeyword(
            @Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT t FROM TravelPost t WHERE " +
            "t.nation = :nation AND(" +
            "LOWER(t.title) LIKE %:keyword% OR " +
            "LOWER(t.subtitle) LIKE %:keyword% OR " +
            "LOWER(t.nation) LIKE %:keyword%)")
    Page<TravelPost> findByNationAndKeyword(
            @Param("nation") String nation,
            @Param("keyword") String keyword,
            Pageable pageable);

}
