package com.meetup.meetup.report.reportdto;

import com.meetup.meetup.report.Report;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReportResponseDto {
    private Long report_id; // 신고 ID
    private Long user_id; //신고된 회원 ID
    private String report_title; // 신고 제목
    private String report_category; // 신고 카테고리
    private String content; //신고내용
    private LocalDateTime report_date; //신고 날짜
    private Long user_id2; // 신고한 회원(외래키id)
    private Long admin_id; // 관리자 id

    public ReportResponseDto(Report report) {
        this.report_id = report.getReport_id();
        this.user_id = report.getUser_id();
        this.report_title = report.getReport_title();
        this.report_category = report.getReport_category().name();
        this.content = report.getContent();
        this.report_date = report.getReport_date();
        this.user_id2 = report.getUser_id2();
        this.admin_id = report.getAdmin_id().getId();

    }

}
