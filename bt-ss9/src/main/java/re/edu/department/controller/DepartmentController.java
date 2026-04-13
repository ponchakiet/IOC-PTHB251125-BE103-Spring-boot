package re.edu.department.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import re.edu.department.dto.request.DepartmentDTO;
import re.edu.department.dto.response.ApiResponse;
import re.edu.department.dto.response.DepartmentResponse;
import re.edu.department.service.IDepartmentService;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final IDepartmentService service;
    @PostMapping
    public ResponseEntity<ApiResponse<DepartmentResponse>> createDepartment(@Valid @RequestBody DepartmentDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<DepartmentResponse>builder()
                        .status("SUCCESS")
                        .message("Tạo phòng ban thành công")
                        .data(service.createDepartment(dto))
                        .build()
        );
    }
}
