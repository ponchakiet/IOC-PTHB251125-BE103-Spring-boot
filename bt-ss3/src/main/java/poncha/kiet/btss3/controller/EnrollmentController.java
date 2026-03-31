package poncha.kiet.btss3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poncha.kiet.btss3.model.ApiResponse;
import poncha.kiet.btss3.model.Enrollment;
import poncha.kiet.btss3.service.EnrollmentService;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private final EnrollmentService service;

    @Autowired
    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Enrollment>>> getAll() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy danh sách đăng ký thành công", service.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> getOne(@PathVariable int id) {
        try {
            return ResponseEntity.ok(new ApiResponse<>(true, "Tìm thấy lượt đăng ký", service.getById(id)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Enrollment>> create(@RequestBody Enrollment e) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Đăng ký thành công", service.create(e)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> update(@PathVariable int id, @RequestBody Enrollment e) {
        try {
            return ResponseEntity.ok(new ApiResponse<>(true, "Cập nhật đăng ký thành công", service.update(id, e)));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> delete(@PathVariable int id) {
        try {
            return ResponseEntity.ok(new ApiResponse<>(true, "Hủy đăng ký thành công", service.delete(id)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage()));
        }
    }
}
