package com.ca1yp2.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "travel_post")
@Getter
@Setter
public class TravelPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imgurl;
    private String imgalt;
    private String nation;
    private String title;
    private String subtitle;

    @Lob
    private String content;

    private int hit = 0;
    private int likeCount = 0;

    private LocalDateTime wdate = LocalDateTime.now();

}
