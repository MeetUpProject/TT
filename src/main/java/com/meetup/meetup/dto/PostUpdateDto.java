package com.meetup.meetup.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostUpdateDto {
    private Long id;              // 수정 대상 게시글 ID
    private String title;
    private String content;
    private String meetTime;
    private int isCruit;
    private int likeCount;
    private int minAge;
    private int maxAge;
    private String place;
}

