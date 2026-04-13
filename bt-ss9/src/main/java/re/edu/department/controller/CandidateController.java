package re.edu.department.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.department.dto.request.CandidateApplyDTO;
import re.edu.department.dto.response.ApiResponse;
import re.edu.department.dto.response.CandidateResponse;
import re.edu.department.dto.response.DepartmentResponse;
import re.edu.department.model.Candidate;
import re.edu.department.service.ICandidateService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/candidates/apply")
public class CandidateController {
    private final ICandidateService service;
    @PostMapping
    public ResponseEntity<ApiResponse<CandidateResponse>> apply(@ModelAttribute CandidateApplyDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<CandidateResponse>builder()
                        .status("SUCCESS")
                        .message("Tạo ứng viên thành công")
                        .data(service.apply(dto))
                        .build()
        );
    }

}
