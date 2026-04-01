package poncha.kiet.ss4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poncha.kiet.ss4.entity.ApiResponse;
import poncha.kiet.ss4.entity.Instructor;
import poncha.kiet.ss4.entity.InstructorCreateRequest;
import poncha.kiet.ss4.service.InstructorService;

@RestController
@RequestMapping("/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> createInstructor(@RequestBody InstructorCreateRequest instructor){
        instructorService.createInstructor(instructor);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true,"Course created successfully!"));
    }
}
