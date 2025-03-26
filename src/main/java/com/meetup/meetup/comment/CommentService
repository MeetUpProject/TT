package com.meetup.meetup.comment;

import com.meetup.meetup.comment.commentdto.CommentCreateDto;
import com.meetup.meetup.comment.commentdto.CommentResponseDto;
import com.meetup.meetup.comment.commentdto.CommentUpdateDto;
import com.meetup.meetup.post.Post;
import com.meetup.meetup.post.PostRepository;
import com.meetup.meetup.post.postdto.PostResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    // 댓글 생성 - Create
    public CommentCreateDto createComment(CommentCreateDto commentCreateDto) {
        //1. Post 객체 가져오기 (PostID가 DTO에 있어야 함)
        Post post = postRepository.findById(commentCreateDto.getPostId2())
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 게시글이 존재하지 않습니다."));

        //2. Comment 엔티티 생성
        Comment newComment = new Comment();

        newComment.setPost(post);
        newComment.setContent(commentCreateDto.getContent());
        newComment.setCreatedTime(LocalDateTime.now());
        newComment.setEditedTime(LocalDateTime.now());
        newComment.setParentId(commentCreateDto.getParentId());

        //3. DB 저장
        commentRepository.save(newComment);
        //4. 저장된 댓글 DTO로 변환 후 반환
        return commentCreateDto;
    }




    // 댓글 작성 - Read
    // CommentResponseDto 응답을 보여주도록!
    public CommentResponseDto readComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글의 댓글이 존재하지 않습니다."));

        return new CommentResponseDto(comment);
    }




    // 댓글 업데이트 - Update
    @Transactional
    public CommentCreateDto updateComment(Long commentId, CommentUpdateDto commentUpdateDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글의 댓글이 존재하지 않습니다."));

        // 댓글 내용 변경
        if (commentUpdateDto.getContent() != null) {
            comment.setContent(commentUpdateDto.getContent());
            comment.setEditedTime(LocalDateTime.now());
        }

        Comment updated = commentRepository.save(comment);
        return new CommentCreateDto(updated);
    }




    // 댓글 삭제 - Delete
    public CommentResponseDto deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글의 댓글이 존재하지 않습니다."));

        commentRepository.deleteById(commentId);

    }
}
