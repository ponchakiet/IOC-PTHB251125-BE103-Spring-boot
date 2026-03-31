package poncha.kiet.btss3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poncha.kiet.btss3.model.ApiResponse;
import poncha.kiet.btss3.model.Course;
import poncha.kiet.btss3.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService service;

    @Autowired
    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAll() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy danh sách khóa học thành công", service.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getOne(@PathVariable int id) {
        try {
            Course c = service.getById(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Tìm thấy khóa học", c));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> create(@RequestBody Course c) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Tạo khóa học thành công", service.create(c)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> update(@PathVariable int id, @RequestBody Course c) {
        try {
            return ResponseEntity.ok(new ApiResponse<>(true, "Cập nhật khóa học thành công", service.update(id, c)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> delete(@PathVariable int id) {
        try {
            return ResponseEntity.ok(new ApiResponse<>(true, "Xóa khóa học thành công", service.delete(id)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage()));
        }
    }
}
