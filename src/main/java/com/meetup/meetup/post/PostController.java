package com.meetup.meetup.post;

import com.meetup.meetup.post.postdto.PostCreateDto;
import com.meetup.meetup.post.postdto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*    ;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    //    PostController
    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostCreateDto> create(@RequestBody Post post) {
        PostCreateDto postCreateDto = postService.createPost(post);
        return ResponseEntity.ok()
                .body(postCreateDto);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<PostResponseDto> read(@PathVariable Long id) {
        PostResponseDto postResponseDto = postService.getPost(id);
        return ResponseEntity.ok()
                .body(postResponseDto);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<PostCreateDto> update(@PathVariable Long id, @RequestBody Post post) {
        PostCreateDto postCreateDto = postService.updatePost(id,post);

        return ResponseEntity.ok()
                .body(postCreateDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PostResponseDto> delete(@PathVariable Long id) {
        PostResponseDto postResponseDto = postService.deletePost(id);
        return ResponseEntity.ok()
                .body(postResponseDto);
    }
}
