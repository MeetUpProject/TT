package com.meetup.meetup.PostPackage.comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
//1. comment id 수정
//2. commentRepository jpa상속
//3. 일시적으로 스프링 시큐리티 삭제
//4. h2 사용중
