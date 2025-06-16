package com.ca1yp2.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ca1yp2.backend.entity.PostImg;
import com.ca1yp2.backend.repository.GuestBookRepository;
import com.ca1yp2.backend.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final GuestBookRepository guestBookRepository;
    private final FileService fileService;

    //댓글 전체 목록
    @GetMapping("/guestbook")
    public ResponseEntity<?> getGuestBook(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("현재 사용자: " + auth.getName());
        return ResponseEntity.ok(guestBookRepository.findAll());
    }

    @DeleteMapping("/delguest/{id}")
    public ResponseEntity<?> deleteGuest(@PathVariable Long id){
        if(!guestBookRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        guestBookRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    //파일 업로드
    @PostMapping("/uploadimage")
    public Map<String, String> uploadImages(
        @RequestParam MultipartFile file,
        @RequestParam String altText,
        @RequestParam String tempPostId
    ){
        String saveName = fileService.saveContentsImage(file);

        PostImg img = new PostImg();
        img.setPostid(Long.parseLong(tempPostId));
        img.setAltText(altText);
        img.setNimgname(saveName);
        img.setOimgname(file.getOriginalFilename());
        img.setExt(fileService.getExt(saveName));
        img.setSize(file.getSize());

        String url = "/travel/contents/" + saveName;
        Map<String, String> rs = new HashMap<>();
        rs.put("url", "http://localhost:8081/upload/" + url);
        rs.put("alt", altText);
        return Map.of("url", url);
    }

}
