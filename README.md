# 🧳 여행 블로그 백엔드 프로젝트

Spring Boot 3.4.6과 Java 17 기반의 여행 블로그 백엔드 서버입니다.  
사용자 인증, 여행 후기 관리, 관광정보 API 연동 등 RESTful API를 제공합니다.

---

## 🔧 기술 스택

- Java 17
- Spring Boot 3.4.6
- Spring Data JPA
- Spring Security
- MySQL Connector/J
- Lombok
- JJWT (JSON Web Token)
- dotenv-java (환경변수 관리)
- JUnit + Spring Security Test (테스트)

---

## 🌟 주요 기능

- 🔐 JWT 기반 회원가입, 로그인 및 인증 처리
- ✍️ 여행 후기 게시글 CRUD API
- 📅 여행 일정 관리 API (FullCalendar 연동용)
- 🗺️ 한국관광공사 관광정보 API 연동
- 📦 이미지 업로드 및 멀티파트 처리
- 🔐 Google reCAPTCHA, Spring Security 통한 보안 강화
- 🧪 통합 테스트 환경 지원

---

## 📁 프로젝트 구조

```
travelblog_back
├── src/
│    ├── main/
│    │    ├── java/com/ca1yp2/backend/
│    │    │     ├── component/
│    │    │     │       └── JwtAuthenticationFilter.java
│    │    │     ├── config/
│    │    │     │      └── DotenvConfig.java
│    │    │     ├── controller/
│    │    │     │       ├── AdminController.java
│    │    │     │       ├── MainController.java
│    │    │     │       ├── PhotoController.java
│    │    │     │       └── TravelController.java
│    │    │     ├── dto/
│    │    │     │    └── GuestBookDto.java
│    │    │     ├── entity/
│    │    │     │     ├── GuestBook.java
│    │    │     │     ├── MyPhoto.java
│    │    │     │     ├── PhotoCategory.java
│    │    │     │     ├── PostImg.java
│    │    │     │     ├── TravelPost.java
│    │    │     │     └── User.java
│    │    │     ├── repository/
│    │    │     │        ├── GuestBookRepository.java
│    │    │     │        ├── MyPhotoRepository.java
│    │    │     │        ├── PhotoCategoryRepository.java
│    │    │     │        ├── PostImgRepository.java
│    │    │     │        ├── TravelPostRepository.java
│    │    │     │        └── UserRepository.java
│    │    │     ├── security/
│    │    │     │       ├── JwtUtil.java
│    │    │     │       └── SecurityConfig.java
│    │    │     ├── service/
│    │    │     │      ├── CaptchaService.java
│    │    │     │      ├── FileService.java
│    │    │     │      └── MyPhotoService.java
│    │    │     └── BackendApplication.java
│    │    ├── resources/
│    │            ├── static
│    │            │      └── upload
│    │            ├── templates
│    │            │
│    │            └── application.yml
│    └── test/
├── .env
├── .gitattributes
├── .gitignore
├── build.gradle
├── gradlew
├── gradelw.bat
├── HELP.md
├── README.md
└── settings.gradle
```

---

## ▶️ 실행 방법

BackendApplication.java로 실행 가능

---

## 🛠️ 환경 변수 및 설정 (dotenv-java 사용)
**.env** 파일 예시:

```.env
SPRING_DATASOURCE_URL=your_url
SPRING_DATASOURCE_USERNAME=your_username
SPRING_DATASOURCE_PASSWORD=your_password

JWT_SECRET=your_jwt_secret_key
```
- dotenv-java 라이브러리를 사용해 환경변수를 .env 파일에서 로드합니다.
- 민감정보는 .gitignore에 포함해 관리하세요.

---

## ✅ 주의사항 및 팁
- JWT 토큰은 Authorization: Bearer <token> 헤더로 전달
- DTO와 Entity 분리로 유지보수성 향상
- 외부 관광정보 API 호출은 RestTemplate 또는 WebClient 활용
- 프로덕션 배포 시 .env 파일 보안 강화 및 별도 관리 권장

---

## 📄 라이선스

MIT 라이선스 (MIT License)

저작권 (c) 2025 ca1yp2

본 프로젝트는 학습 및 개인 포트폴리오 용도로 제작되었습니다. 본 소프트웨어 및 관련 문서 파일(이하 "소프트웨어")을 무상으로 획득한 모든 사람에게 소프트웨어를 제한 없이 사용, 복사, 수정, 병합, 출판, 배포, 서브라이선스 및 판매할 권리를 허가합니다.

단, 위 저작권 표시와 이 허가 표시를 소프트웨어의 모든 복사본 또는 중요한 부분에 포함시켜야 합니다.

본 소프트웨어는 상품성, 특정 목적 적합성 및 비침해에 대한 보증 없이 "있는 그대로" 제공됩니다. 저작권자 또는 저작권 보유자는 소프트웨어 사용 또는 기타 거래와 관련하여 발생하는 어떠한 청구, 손해 또는 기타 책임에 대해서도 책임을 지지 않습니다.

---

MIT License

Copyright (c) 2025 ca1yp2

This project was created for learning and personal portfolio purposes. Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
