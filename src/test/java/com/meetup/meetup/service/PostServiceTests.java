package com.meetup.meetup.service;


import com.meetup.meetup.dto.PageRequestDto;
import com.meetup.meetup.dto.PageResponseDto;
import com.meetup.meetup.dto.PostResponseDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class PostServiceTests {
    @Autowired
    private PostService postService;
    @Test
    public void testRegister(){
        log.info(postService.getClass().getName());
    }
    @Test
    public void testList(){
        PageRequestDto pageRequestDto = PageRequestDto.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();
        PageResponseDto<PostResponseDto> postResponseDtoPageRequestDto = postService.list(pageRequestDto);
        log.info(postResponseDtoPageRequestDto);
    }
}
