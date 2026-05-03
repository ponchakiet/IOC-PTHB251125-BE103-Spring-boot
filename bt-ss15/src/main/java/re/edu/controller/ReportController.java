package re.edu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import re.edu.dto.response.RevenueResponse;
import re.edu.service.ReportService;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/revenue")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RevenueResponse> getRevenue(
            @RequestParam String type,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month) {
        return ResponseEntity.ok(reportService.getRevenue(type, year, month));
    }
}
