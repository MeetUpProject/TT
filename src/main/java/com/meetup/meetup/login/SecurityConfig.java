package com.meetup.meetup.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    // spring security에서 제공하는 사용자 인증 -> 빈 등록 필요
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception{
        return config.getAuthenticationManager();
    }

    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{ // http 요청에 대한 보안, 인증, 인가, 로그아웃 설정
        return http
                .csrf(AbstractHttpConfigurer::disable) // 위조 요청 보호 비활성화 : rest api는 인증정보 보관 X -> 필요 없음
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) // h2 콘솔 열기 위함 -> 실제 개발 시 X
                .formLogin(AbstractHttpConfigurer::disable) // 세션 이용 로그인 비활성화
                .httpBasic(AbstractHttpConfigurer::disable) //
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 무상태 설정
                .authorizeHttpRequests(auth -> auth.
                        requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/configuration/**",
                                "/h2-console/**",
                                "/login",
                                "/signup",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/favicon.ico").permitAll() // h2, 로그인, 로그아웃, 정적 파일, 브라우저 아이콘 접근 허용
                        .anyRequest().authenticated()) // 그 외 요청은 인증 필요
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // spring security에 jwtFilter 추가
                .build();


    }




}
