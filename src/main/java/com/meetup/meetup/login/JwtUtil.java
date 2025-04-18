package com.meetup.meetup.login;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

//    @Value("${jwt.secret}") // application.properties에서 값을 가져옴
//    private final String secretKey;
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    private final long ACCESS_EXPIRATION_TIME = 1000 * 60 * 60; // 최초 발급 : 유효 시간 1시간으로 설정
    private final long REFRESH_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 14; // 갱신 : 유효시간 2주로 설정

//    public JwtUtil(@Value("${jwt.secret}") String secretKey){ // null 방지를 위해 생성자 매개변수에 secret 값 넣음
//        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes()); // secret key 인코딩
//    }

    // Access Token 발급
    public String generateAccessToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    // Refresh Token 발급
    public String generateRefreshToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

//    // Jwt 생성
//    public String generateToken(String userId){
//        return Jwts.builder()
//                .setSubject(userId) // 토큰에 사용자 id 저장
//                .setIssuedAt(new Date()) // 토큰 생성 시간
//                .setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME)) // 토큰 만료 시간 : 현재 시간 - 유효 시간
//                .signWith(SignatureAlgorithm.HS512, secretKey) // 알고리즘 서명
//                .compact(); // 최종 문자열 생성
//    }

    // 사용자 정보 추출
    public String extractUserId(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key) // 비밀키 넣기
                .build()
                .parseClaimsJws(token) // 서명(signature)이 있는 토큰 파싱 -> Claims 추출
                .getBody() // Claims 정보 가져오기
                .getSubject(); // 정보 중 사용자 정보 가져오기
    }

    // 토큰 유효성 검증
    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) { // 유효 시간 만료
            System.out.println(" 토큰이 만료되었습니다. ");
        } catch (SignatureException e) { // 서명 오류
            System.out.println(" 토큰 서명이 올바르지 않습니다. ");
        } catch (MalformedJwtException e) { // 형식 오류
            System.out.println(" 토큰 형식이 올바르지 않습니다. ");
        } catch (UnsupportedJwtException e) { // 다른 알고리즘 사용된 토큰
            System.out.println(" 지원하지 않는 토큰 형식입니다. ");
        } catch (IllegalArgumentException e) { // 토큰이 null 또는 없는 경우
            System.out.println(" 토큰이 없습니다. ");
        } catch (Exception e) { // 그 외 오류
            System.out.println(" 토큰 검증 중 예상치 못한 오류가 발생했습니다. ");
        }
        return false;
    }

}
