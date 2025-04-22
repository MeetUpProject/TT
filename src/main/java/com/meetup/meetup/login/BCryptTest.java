package com.meetup.meetup.login;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode("1234");
        System.out.println("암호화된 비밀번호: " + encodedPassword);
    }
}
