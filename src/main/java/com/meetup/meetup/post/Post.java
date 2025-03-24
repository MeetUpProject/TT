package com.meetup.meetup.post;

import com.meetup.meetup.comment.Comment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.security.Timestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@DynamicUpdate
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "제목은 필수란 입니다")
    @NotNull
    private String title;

    @NotNull
    private String content;
    private String meet_time;//만남시간
    private int cruit;
    private int is_cruit;
    private int like_count;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private int view_count;

    @Column(nullable = true)
    private int min_age;
    @Column(nullable = true)
    private int max_age;
    private String place;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> comments;


    /*
     * 회원, 후기 테이블 만들어지면 매핑 해야됨
     * */
}
