package com.meetup.meetup.comment;

import com.meetup.meetup.post.Post;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //

    //부모댓글 아이디
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Comment parentId;


    //내용
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    //댓글 작성 시간
    @Column(name = "created_t", updatable = false)
    @CreatedDate
    private LocalDateTime createdTime;

    //댓글 수정 시간
    @Column(name = "edited_t")
    @LastModifiedDate
    private LocalDateTime editedTime;

    //모집글 제목(외래키로 연결되어 있음)
    @ManyToOne
    @JoinColumn(name = "post_id") //게시글 외래키 post_id로 임의로 지정해서 코드 짬
    private Post post;
}
