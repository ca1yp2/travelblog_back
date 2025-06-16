package com.ca1yp2.backend.config;

import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class DotenvConfig {

    public DotenvConfig(){
        Dotenv dotenv = Dotenv.configure()
                              .filename(".env")
                              .load();
        
        System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        System.setProperty("RECAPTCHA_SECRET", dotenv.get("RECAPTCHA_SECRET"));
        System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
    }

}
