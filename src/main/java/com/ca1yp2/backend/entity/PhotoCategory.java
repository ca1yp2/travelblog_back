package com.ca1yp2.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "photo_category")
@Data
public class PhotoCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
