package com.ca1yp2.backend.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ca1yp2.backend.dto.GuestBookDto;
import com.ca1yp2.backend.entity.GuestBook;
import com.ca1yp2.backend.entity.TravelPost;
import com.ca1yp2.backend.repository.GuestBookRepository;
import com.ca1yp2.backend.repository.TravelPostRepository;
import com.ca1yp2.backend.service.CaptchaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/blog")
public class TravelController {

    private final GuestBookRepository guestBookRepository;
    private final TravelPostRepository travelPostRepository;

    private final CaptchaService captchaService;

    @GetMapping("/travel/list")
    public ResponseEntity<Page<TravelPost>> getAllTraves(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(required = false) String nation,
            @RequestParam(required = false) String keyword) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<TravelPost> result;

        boolean isNation = nation != null && !nation.isBlank();
        boolean isKeyword = keyword != null && !keyword.isBlank();

        // 국가별이고 검색, 국가별, 검색, 그냥
        if (isNation && isKeyword) {
            result = travelPostRepository.findByNationAndKeyword(
                    nation.trim(), keyword.trim().toLowerCase(), pageable);
        } else if (isNation) {
            result = travelPostRepository.findByNation(nation.trim(), pageable);
        } else if (isKeyword) {
            result = travelPostRepository.findByKeyword(keyword.trim().toLowerCase(), pageable);
        } else {
            result = travelPostRepository.findAll(pageable);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/travel/groupByNation")
    public ResponseEntity<Map<String, List<TravelPost>>> getGroupByNation() {
        List<TravelPost> list = travelPostRepository.findAll(
                Sort.by(Sort.Direction.ASC, "nation"));
        Map<String, List<TravelPost>> grouped = list.stream()
                .collect(Collectors.groupingBy(TravelPost::getNation));
        return ResponseEntity.ok(grouped);
    }

    @GetMapping("/travel/view/{id}")
    public ResponseEntity<TravelPost> getTravelById(@PathVariable Long id) {

        return travelPostRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/travel/write")
    public ResponseEntity<?> writeGuestBook(@RequestBody GuestBookDto dto) {
        if (!captchaService.verifyCaptcha(dto.getCaptchaToken())) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("캡차인증에 실패했습니다.");
        }

        GuestBook entity = new GuestBook();
        entity.setPostId(dto.getPostId());
        entity.setContent(dto.getContent());
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        entity.setTitle(dto.getTitle());
        entity.setDate(LocalDateTime.now());
        guestBookRepository.save(entity);
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/travel/guest/list/{postId}")
    public ResponseEntity<List<GuestBook>> getGuestBookPostId(
            @PathVariable Long postId) {
        List<GuestBook> list = guestBookRepository.findByPostIdOrderByIdDesc(postId);
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/travel/guest/delete/{id}")
    public ResponseEntity<?> deleteGuestBook(
            @PathVariable Long id,
            @RequestParam String password) {
        Optional<GuestBook> optional = guestBookRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        GuestBook guest = optional.get();
        if (!guest.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("비밀번호가 일치하지 않습니다.");
        }
        guestBookRepository.deleteById(id);
        return ResponseEntity.ok("댓글이 삭제되었습니다.");
    }

}