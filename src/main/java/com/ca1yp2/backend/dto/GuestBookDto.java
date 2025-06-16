package com.ca1yp2.backend.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GuestBookDto {
    private long id;
    private long postId;
    private String title;
    private String name;
    private String password;
    private String email;
    private String content;
    private LocalDateTime date;
    private String captchaToken;
}
