package com.meetup.meetup.report;

import com.meetup.meetup.admin.AdminRepository;
import com.meetup.meetup.report.reportdto.ReportRequestDto;
import com.meetup.meetup.report.reportdto.ReportResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.meetup.meetup.admin.Admin;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final AdminRepository adminRepository;

    // 전체 신고 조회
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    // 특정 신고 조회
    public Optional<Report> getReportById(Long id) {
        return reportRepository.findById(id);
    }

    // 신고 등록 - 일반 유저 사용 예정
    public Report createReport(ReportRequestDto reportDto) {
        Report report = new Report();
        report.setUser_id(reportDto.getUser_id());
        report.setReport_title(reportDto.getReport_title());
        report.setReport_category(ReportCategory.valueOf(reportDto.getReport_category())); // 문자열 -> ENUM 변환
        report.setContent(reportDto.getContent());
        report.setUser_id2(reportDto.getUser_id2());

        Admin admin = adminRepository.findById(reportDto.getAdmin_id())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 관리자입니다."));
        report.setAdmin_id(admin);

        return reportRepository.save(report);
    }

    // 신고 삭제
    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }
}
