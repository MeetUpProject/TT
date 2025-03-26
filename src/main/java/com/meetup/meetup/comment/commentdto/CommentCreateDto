package com.meetup.meetup.comment.commentdto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.meetup.meetup.comment.Comment;
import com.meetup.meetup.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentCreateDto {
    private Long id;
    private Comment parentId;
    private String content;
    private Long postId2;


    public CommentCreateDto(Comment comment) {
        this.id = comment.getId();
        this.parentId = comment.getParentId();
        this.content = comment.getContent();
        this.postId2 = comment.getPost().getId(); //Post 엔티티에서 ID 가져오기
    }
}
