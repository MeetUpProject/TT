package com.meetup.meetup.comment.commentdto;

import com.meetup.meetup.comment.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Comment parent_id; //부모댓글
    private String content; // 내용
    private LocalDateTime created_t; //생성시간
    private LocalDateTime edited_t; // 수정시간
    private Long post_id2; //답변 작성한 게시글

    public CommentResponseDto(Comment comment) {
        this.parent_id = comment.getParentId();
        this.content = comment.getContent();
        this.created_t = comment.getCreatedTime();
        this.edited_t = comment.getEditedTime();
        this.post_id2 = comment.getPost().getId();
    }
}
