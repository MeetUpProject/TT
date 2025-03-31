package com.meetup.meetup.report;


import com.meetup.meetup.report.reportdto.ReportRequestDto;
import com.meetup.meetup.report.reportdto.ReportResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    // 전체 신고 조회
    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    // 특정 신고 조회
    @GetMapping("/{id}")
    public ResponseEntity<ReportResponseDto> getReportById(@PathVariable Long id) {
        Optional<Report> report = reportService.getReportById(id);

        if (report.isPresent()) {
            ReportResponseDto dto = new ReportResponseDto(report.get());
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //신고 등록
    @PostMapping("/create")
    public ResponseEntity<ReportResponseDto> createReport(@RequestBody ReportRequestDto reportDto) {
        Report savedReport = reportService.createReport(reportDto);
        ReportResponseDto responseDto = new ReportResponseDto(savedReport);
        return ResponseEntity.ok(responseDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByid(@PathVariable Long id) {
        Optional<Report> report = reportService.getReportById(id);

        if (report.isPresent()) {
            reportService.deleteReport(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
