package com.meetup.meetup.login;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@WebFilter
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        String username = null;

        // JWT 토큰 추출
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            token =  bearerToken.substring(7); // "Bearer " 제거 후 반환

            if(jwtUtil.validateToken(token)){ // 존재 및 유효한 토큰인지 검사
                username = jwtUtil.extractUserId(token);
            }
        }




        // SecurityContext에 인증 정보 등록

        // 다음 필터 요청으로 넘기기

    }

    private String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization"); // Authorization에서 헤더 가져옴
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7); // "Bearer " 제거 후 반환
        }
        return null; // 잘못된 형식이면 null 반환
    }
}
