package com.meetup.meetup.report;

import com.meetup.meetup.admin.Admin;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

//admin은 잘 모르겠지만 신고기능은 다 구현된거같아 admin도 data.sql로 했다가 지우고 java로 아예 고정시켜버렸어

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long report_id; //신고 ID

    @Column(name = "user_id", nullable = false)
    private Long user_id; // 신고된 회원

    @Column(name = "report_title", nullable = false)
    private String report_title; // 신고 제목

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportCategory report_category; // 신고 카테고리

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @CreatedDate
    private LocalDateTime report_date; //신고 날짜

    @Column(name = "user_id2", nullable = false)
    private Long user_id2; //회원 id (외래키)

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin_id; // 관리자 id

}
