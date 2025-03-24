package com.meetup.meetup.post.postdto;

import com.meetup.meetup.comment.Comment;
import com.meetup.meetup.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostResponseDto {
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

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.meet_time = post.getMeet_time();
        this.cruit = post.getCruit();
        this.is_cruit = post.getIs_cruit();
        this.like_count = post.getLike_count();
        this.updated_at = post.getUpdated_at();
        this.created_at = post.getCreated_at();
        this.view_count = post.getView_count();
        this.min_age = post.getMin_age();
        this.max_age = post.getMax_age();
        this.place = post.getPlace();
        this.comments = post.getComments();
    }
}
