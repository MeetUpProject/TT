package com.meetup.meetup.admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
    // 관리자 아이디로 사용자 정보 찾으려고 일부러 만듦
}
