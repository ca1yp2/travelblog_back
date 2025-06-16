package com.ca1yp2.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		 Dotenv dotenv = Dotenv.configure()
                              .filename(".env") // .env 파일명과 위치 (기본값이므로 생략 가능)
                              .load();

        System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		System.setProperty("RECAPTCHA_SECRET", dotenv.get("RECAPTCHA_SECRET"));
		System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));

		SpringApplication.run(BackendApplication.class, args);
	}

}
