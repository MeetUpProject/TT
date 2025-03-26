package com.meetup.meetup.comment;

import com.meetup.meetup.comment.commentdto.CommentCreateDto;
import com.meetup.meetup.comment.commentdto.CommentResponseDto;
import com.meetup.meetup.comment.commentdto.CommentUpdateDto;
import com.meetup.meetup.post.Post;
import com.meetup.meetup.post.PostService;
import com.meetup.meetup.post.postdto.PostCreateDto;
import com.meetup.meetup.post.postdto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    // CommentController
    private final PostService postService; // 특정 게시글 추가 위해
    private final CommentService commentService; // 댓글 저장 서비스

    @PostMapping("/create")
    public ResponseEntity<CommentCreateDto> createComment(@RequestBody CommentCreateDto requestDto) {
        CommentCreateDto commentCreateDto = commentService.createComment(requestDto);
        return ResponseEntity.ok()
                .body(commentCreateDto);
    }


    @GetMapping("/read/{id}")
    public ResponseEntity<CommentResponseDto> readComment(@PathVariable Long id) {
        CommentResponseDto commentResponseDto = commentService.readComment(id);
        return ResponseEntity.ok()
                .body(commentResponseDto);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<CommentCreateDto> updateComment(@PathVariable Long id, @RequestBody CommentUpdateDto requestDto) {
        CommentCreateDto commentCreateDto = commentService.updateComment(id, requestDto);
        return ResponseEntity.ok()
                .body(commentCreateDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CommentResponseDto> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
