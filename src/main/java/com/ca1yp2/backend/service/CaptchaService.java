package com.ca1yp2.backend.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CaptchaService {
    
    @Value("${recaptcha.secret}")
    private String recaptchaSecret;

    public boolean verifyCaptcha(String token) {
        String url = "https://www.google.com/recaptcha/api/siteverify?secret={secret}&response={response}";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.postForEntity(
                url, null, Map.class, recaptchaSecret, token);

        Map<String, Object> body = response.getBody();
        return body != null && Boolean.TRUE.equals(body.get("success"));
    }
}
