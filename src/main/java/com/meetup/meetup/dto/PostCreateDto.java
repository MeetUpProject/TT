    package com.meetup.meetup.dto;

    import com.fasterxml.jackson.annotation.JsonInclude;
    import com.meetup.meetup.post.Post;
    import lombok.*;


    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    //추가
    @Builder
    @Data
    @AllArgsConstructor

    public class PostCreateDto {

        private String title;
        private String content;
        private String writer;
        private int is_cruit;
        private String meet_time;
        private String password;

        public PostCreateDto(Post post ) {
            this.title = post.getTitle();
            this.writer = post.getWriter();
            this.content = post.getContent();
            this.is_cruit = post.getIs_cruit();
            this.meet_time = post.getMeet_time();
        }
    }
/*
* private String title;

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
    private List<Comment> comments;*/