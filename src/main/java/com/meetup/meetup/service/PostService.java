package com.meetup.meetup.service;

import com.meetup.meetup.dto.*;

public interface PostService {
    Long register(PostCreateDto postCreateDto);
    PostResponseDto read(Long id);
    void modify(PostUpdateDto postUpdateDto);

    PostResponseDto remove(Long id);

    PageResponseDto<PostResponseDto> list(PageRequestDto pageRequestDto);
}
