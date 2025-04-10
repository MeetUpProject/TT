package com.meetup.meetup.controller;

import com.meetup.meetup.dto.*;
import com.meetup.meetup.post.Post;
import com.meetup.meetup.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Log4j2
public class PostController {

    private final PostService postService;

    /** 게시글 생성 */
    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody PostCreateDto dto) {

        if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body(null); // 비밀번호가 없을 경우
        }
        Long id = postService.register(dto);

        return ResponseEntity.ok(id);
    }


    /** 게시글 조회 */
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> read(@PathVariable Long id) {
        log.info("Read Post ID: {}", id);
        PostResponseDto postResponseDto = postService.read(id);
        return ResponseEntity.ok(postResponseDto);
    }

    /** 게시글 수정 */
    @PatchMapping
    public ResponseEntity<Void> update(@RequestBody PostUpdateDto postUpdateDto) {
        log.info("Update Post: {}", postUpdateDto);
        postService.modify(postUpdateDto);
        return ResponseEntity.noContent().build();
    }

    /** 게시글 삭제 */
    @DeleteMapping("/{id}")
    public ResponseEntity<PostResponseDto> delete(@PathVariable Long id) {
        log.info("Delete Post ID: {}", id);
        PostResponseDto deletedPost = postService.remove(id);
        return ResponseEntity.ok(deletedPost);
    }

    @GetMapping("/list")
    public ResponseEntity<PageResponseDto<PostResponseDto>> list(PageRequestDto dto) {
        return ResponseEntity.ok(postService.list(dto));
    }

    @PostMapping("/{id}/verify-password")
    public ResponseEntity<Void> verifyPassword(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String password = body.get("password");
        PostResponseDto postResponseDto = postService.read(id);

        // 비밀번호 확인
        if (postResponseDto.getPassword().equals(password)) {
            return ResponseEntity.ok().build(); // 비밀번호 맞으면 OK
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 비밀번호 틀리면 FORBIDDEN
        }
    }
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        PostResponseDto postResponseDto = postService.read(id);  // 게시글 조회
        model.addAttribute("post", postResponseDto);  // 게시글 정보를 모델에 추가
        return "post_edit";  // 수정 페이지로 이동
    }









}
