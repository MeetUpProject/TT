package com.meetup.meetup.admin;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminLogin {

    // 관리자 로그인 아이디 정보 만든 클래스
    private final AdminRepository adminRepository;

    @PostConstruct
    public void initAdmin() {
        if (adminRepository.count() == 0) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword("1234");
            admin.setRole("ROLE_ADMIN");

            adminRepository.save(admin);

        }
    }
}
