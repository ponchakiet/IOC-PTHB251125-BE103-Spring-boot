package poncha.kiet.ss4.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import poncha.kiet.ss4.dto.CourseResponse;
import poncha.kiet.ss4.entity.ApiResponse;
import poncha.kiet.ss4.entity.CourseStatus;
import poncha.kiet.ss4.entity.PageResponse;
import poncha.kiet.ss4.service.CourseService;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CourseResponse>>> findAll(
            @PageableDefault Pageable pageable
    ){
        return new ResponseEntity<>(new ApiResponse<>(true, "Ok",courseService.getPagedCourses(pageable)), HttpStatus.OK);
    }

    @GetMapping("/by-status")
    public ResponseEntity<ApiResponse<PageResponse<CourseResponse>>> getCoursesByStatus(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(defaultValue = "ACTIVE") CourseStatus status // Trạng thái mặc định
    ) {
        PageResponse<CourseResponse> response = courseService.getPagedCoursesByStatus(
                page, size, sortBy, direction, status
        );
        return ResponseEntity.ok(new ApiResponse<>(true, "Ok", response));
    }
}
