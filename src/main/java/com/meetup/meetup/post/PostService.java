package com.meetup.meetup.post;

import com.meetup.meetup.post.postdto.PostCreateDto;
import com.meetup.meetup.post.postdto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

       public PostCreateDto createPost(Post post){
        Post   newPost = new Post();
       newPost.setTitle(post.getTitle());
       newPost.setContent(post.getContent());
       newPost.setMeet_time(post.getMeet_time());
       newPost.setCruit(post.getCruit());
       newPost.setIs_cruit(1);
       newPost.setLike_count(0);
       newPost.setCreated_at(LocalDateTime.now());
       newPost.setUpdated_at(LocalDateTime.now());
       newPost.setMax_age(post.getMax_age());
       newPost.setMin_age(post.getMin_age());
       newPost.setPlace(post.getPlace());

       newPost.setComments(post.getComments());

       postRepository.save(newPost);
       /*회원 후기 테이블 추가시 연결 필요d*/



        return new PostCreateDto(post);
        }

       public PostResponseDto getPost(long id){
           Post post = postRepository.findById(id)
                   .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글입니다."));

        return new PostResponseDto(post);
       }

       //후에 회원 정보 가져와야함.
       public PostCreateDto updatePost(Long id, Post updating) {
           Post oldPost = postRepository.findById(id)
                   .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

           //  필요한 값만 변경
           if (updating.getTitle() != null) {
               oldPost.setTitle(updating.getTitle());
           }
           if (updating.getContent() != null) {
               oldPost.setContent(updating.getContent());
           }
           if (updating.getMeet_time() != null) {
               oldPost.setMeet_time(updating.getMeet_time());
           }
           if (updating.getIs_cruit() != 0) { // int 기본값
               oldPost.setIs_cruit(updating.getIs_cruit());
           }
           if (updating.getLike_count() != 0) {
               oldPost.setLike_count(updating.getLike_count());
           }
           if (updating.getUpdated_at() != null) {
               oldPost.setUpdated_at(updating.getUpdated_at());
           }
           if (updating.getMax_age() != 0) {
               oldPost.setMax_age(updating.getMax_age());
           }
           if (updating.getMin_age() != 0) {
               oldPost.setMin_age(updating.getMin_age());
           }
           if (updating.getPlace() != null) {
               oldPost.setPlace(updating.getPlace());
           }

           // 변경 사항 저장
           postRepository.save(oldPost);

           return new PostCreateDto(oldPost);
       }
       public PostResponseDto deletePost(long id) {
           Post post= postRepository.findById(id)
                   .orElseThrow(()-> new IllegalArgumentException("ㅇㅇ"));
           postRepository.deleteById(id);

           return new PostResponseDto(post) ;
       }
       /*
    private String title;
    private String content;
    private String meet_time;
    private int cruit;
    private int is_cruit;
    private int like_count;
    private LocalDateTime updated_at;
    private LocalDateTime created_at;
    private int view_count;

    private int min_age;
    private int max_age;
    private String place;
    private List<Comment> comments;
*/

}
