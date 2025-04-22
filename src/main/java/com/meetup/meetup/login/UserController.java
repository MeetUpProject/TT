package com.meetup.meetup.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RequestDto requestDto) {
        System.out.println("로그인 시도: " + requestDto.getId());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestDto.getId(), requestDto.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication); // spring security context 에 인증 정보 저장

            UserDetails user = (UserDetails) authentication.getPrincipal();// user 정보 불러와서(object) 형변환

            String accessToken = jwtUtil.generateAccessToken(user.getUsername());
            String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

            return ResponseEntity.ok(
                    TokenDto.builder()
                            .grantType("Bearer")// 토큰을 소유하고 있으면 권한이 있다고 판단
                            .accessToken(accessToken)
                            .refreshToken(refreshToken)
                            .build()
            );

        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
        }

    }


}
