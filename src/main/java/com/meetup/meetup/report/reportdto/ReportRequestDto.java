package com.meetup.meetup.report.reportdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportRequestDto {
    private Long user_id; //신고당한 회원
    private String report_title;  // 제목
    private String report_category; // ENUM 문자열
    private String content;
    private Long user_id2;        // 신고한 사람 ID
    private Long admin_id;        // 관리자 ID
}
