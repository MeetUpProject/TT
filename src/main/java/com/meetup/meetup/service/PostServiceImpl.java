package com.meetup.meetup.service;

import com.meetup.meetup.dto.*;
import com.meetup.meetup.post.Post;
import com.meetup.meetup.post.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService{
    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public Long register(PostCreateDto postCreateDto) {
        Post post = modelMapper.map(postCreateDto, Post.class);
        Long id = postRepository.save(post).getId(); // 여기서 저장이 DB에 반영되어야 함
        return id;
    }

    @Override
    public PostResponseDto read(Long id) {
        return postRepository.findById(id)
                .map(post -> new PostResponseDto(post))
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")); // 예외 처리
    }


    //후에 회원 정보 가져와야함.
    @Override
    public void modify(PostUpdateDto postUpdateDto) {
        Post post = postRepository.findById(postUpdateDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        if (postUpdateDto.getTitle() != null) post.setTitle(postUpdateDto.getTitle());
        if (postUpdateDto.getContent() != null) post.setContent(postUpdateDto.getContent());
        if (postUpdateDto.getMeetTime() != null) post.setMeet_time(postUpdateDto.getMeetTime());
        if (postUpdateDto.getIsCruit() != 0) post.setIs_cruit(postUpdateDto.getIsCruit());
        if (postUpdateDto.getLikeCount() != 0) post.setLike_count(postUpdateDto.getLikeCount());
        if (postUpdateDto.getMinAge() != 0) post.setMin_age(postUpdateDto.getMinAge());
        if (postUpdateDto.getMaxAge() != 0) post.setMax_age(postUpdateDto.getMaxAge());
        if (postUpdateDto.getPlace() != null) post.setPlace(postUpdateDto.getPlace());

        post.setUpdated_at(LocalDateTime.now());

        postRepository.save(post);
    }

    @Override
    public PostResponseDto remove(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        postRepository.delete(post); // deleteById도 OK, delete(post)가 더 명확

        return new PostResponseDto(post); // 삭제 전 데이터 반환
    }

    @Override
    public PageResponseDto<PostResponseDto> list(PageRequestDto pageRequestDto) {
        Pageable pageable = pageRequestDto.getPageable("id");

        Page<Post> result;

        String keyword = pageRequestDto.getKeyword();
        if (keyword != null && !keyword.trim().isEmpty()) {
            // 🔍 검색어가 있을 때는 제목/내용 검색
            result = postRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(
                    keyword, keyword, pageable
            );
        } else {
            // 🔄 검색어 없으면 전체 조회
            result = postRepository.findAll(pageable);
        }

        List<PostResponseDto> dtoList = result.getContent()
                .stream()
                .map(PostResponseDto::new)
                .toList();

        return new PageResponseDto<>(pageRequestDto, dtoList, (int) result.getTotalElements());
    }





}
