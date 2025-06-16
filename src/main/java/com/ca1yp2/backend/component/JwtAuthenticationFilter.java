package com.ca1yp2.backend.component;

import java.io.IOException;
import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ca1yp2.backend.security.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    private final JwtUtil jwtUtil;  //jwt 검증 파싱 클래스

    protected void doFilterInternal(@NonNull HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        //1. 요청받은 Authorization 헤더에서 JWT만 추출
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer")){  //Bearer는 토큰 유형(type)을 나타내는 표준 규약 OAuth2, OpenID, JWT, Connect cf. Bearer, Basic, Digest
            String token = header.substring(7);

            //추출한 token의 유효성 검사
            if(jwtUtil.validateToken(token)){
                //사용자 정보 추출
                String username = jwtUtil.extractUsername(token);
                String role = jwtUtil.extractRole(token);

                //권한 부여
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);

                //인증객체 생성
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, List.of(authority));

                //SecurityContext에 등록
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        //다음 필터로 request 전달
        filterChain.doFilter(request, response);
    }
}
