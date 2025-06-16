package com.ca1yp2.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="post_img")
@Data
public class PostImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postid;
    private String altText;
    private String oimgname;
    private String nimgname;
    private String ext;
    private Long size;

}
