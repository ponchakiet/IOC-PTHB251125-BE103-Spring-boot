package poncha.kiet.btss3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poncha.kiet.btss3.model.ApiResponse;
import poncha.kiet.btss3.model.Instructor;
import poncha.kiet.btss3.service.InstructorService;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
    private final InstructorService service;

    @Autowired
    public InstructorController(InstructorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getAll() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy danh sách giảng viên thành công", service.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> getOne(@PathVariable int id) {
        try {
            Instructor i = service.getById(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Tìm thấy giảng viên", i));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> create(@RequestBody Instructor i) {
        Instructor created = service.create(i);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Tạo mới giảng viên thành công", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> update(@PathVariable int id, @RequestBody Instructor i) {
        try {
            Instructor updated = service.update(id, i);
            return ResponseEntity.ok(new ApiResponse<>(true, "Cập nhật thành công", updated));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> delete(@PathVariable int id) {
        try {
            Instructor deleted = service.delete(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Xóa thành công", deleted));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage()));
        }
    }
}
