package com.meetup.meetup.post;

import com.meetup.meetup.comment.Comment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.security.Timestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
//생성자 자동으로만들게끔 추가, 빌더 패턴 사용
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "제목은 필수란 입니다")
    @NotNull
    private String title;
    private String writer;

    @NotNull
    private String content;
    private String meet_time;//만남시간
    private int cruit;
    private int is_cruit;
    private int like_count;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private int view_count;
    private String password;
    @Column(nullable = true)
    private int min_age;
    @Column(nullable = true)
    private int max_age;
    private String place;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> comments;

    //도메인 방식의 메서드를 사용.직접 setter 사용보다 의미 전달 편해서..
    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }
    @PrePersist
    public void onCreate() {
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }

}
