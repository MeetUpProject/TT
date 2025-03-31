package com.meetup.meetup.login;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDto {
    private String grantType; // 토큰 인증 타입
    private String accessToken; // 접근 토큰
    private String refreshToken; // 갱신 토큰
}
