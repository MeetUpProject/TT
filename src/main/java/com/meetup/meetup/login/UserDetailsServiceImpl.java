package com.meetup.meetup.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired // 생성자 자동 주입
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("회원 정보를 찾을 수 없습니다"));

        GrantedAuthority authority = new SimpleGrantedAuthority(userEntity.getRole().toString());

        return User.builder()
                .username(userEntity.getUserId())
                .password(userEntity.getPassword())
                .authorities(Collections.singletonList(authority))
                .build();

    }
}
