package com.ca1yp2.backend.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ca1yp2.backend.entity.User;
import com.ca1yp2.backend.repository.UserRepository;
import com.ca1yp2.backend.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class MainController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtUtil jwtUtil;

    // @GetMapping("/test")
    // public ResponseEntity<String> test() {
    // return ResponseEntity.ok("API 테스트 성공!");
    // }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());

        if (userOpt.isPresent()
                &&
                passwordEncoder.matches(
                        request.getPassword(), userOpt.get().getPassword())) {
            // token을 만들어 리턴 시킴
            User user = userOpt.get();
            String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
            return ResponseEntity.ok(token);
            // Jwt 토큰 발급해서 보냄

        } else {
            return ResponseEntity.status(401).body("로그인 실패!");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("이미 존재하는 아이디입니다.");
        }
        User newUser = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("ROLE_USER")
                .build();
        userRepository.save(newUser);
        return ResponseEntity.ok("회원가입에 성공했습니다.");
    }

    // 내부 클래스로 LoginRequest를 만들자
    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}

