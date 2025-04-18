package com.meetup.meetup.login;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Table(name = "users") // user 테이블과 매핑
@Entity // 해당 클래스를 엔티티로 인식, CRUD 사용 가능
@Getter
@Builder // 빌더 패턴 자동 구현 -> 가독성 높임, 직관적 객체 생성 가능
public class UserEntity implements UserDetails {

    @Id
    @Column(name = "user_num")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 회원 번호 자동 증가 방식 사용
    private Long userNum; // 회원 관리용 회원 번호

    @Column(name = "user_id", nullable = false)
    private String userId; // 회원가입 및 로그인 시 사용하는 사용자 id

    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // 회원가입 및 로그인 시 사용하는 사용자 비밀번호

    @Column(nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private Integer gender;

    @Column
    private String image;

    @Column(nullable = false)
    private String area;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING) // 가독성을 위함
//    @ElementCollection // db에 저장
    private Role role;


    public enum Role {
        ROLE_USER,
        ROLE_ADMIN;
    }


    /**
     * 권한 리스트
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // <?> 아직 미정된 타입
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    /**
     * 사용자 비밀번호
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * 사용자 이름 - pk
     * @return 사용자 id
     */
    @Override
    public String getUsername() {
        return userId;
    }

    /**
     * 계정 만료(안됨) 여부
     * true : 만료 안됨
     * false : 만료됨
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정 잠김(잠기지 않음) 여부
     * true : 잠기지 않음
     * false : 잠김
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 비밀번호 만료(안됨) 여부
     * true : 만료 안됨
     * false : 만료됨
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 사용자 활성화 여부
     * true : 활성화
     * false : 활성화 X
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
