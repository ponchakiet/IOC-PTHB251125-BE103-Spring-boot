package poncha.kiet.ss4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poncha.kiet.ss4.entity.ApiResponse;
import poncha.kiet.ss4.entity.Instructor;
import poncha.kiet.ss4.entity.InstructorCreateRequest;
import poncha.kiet.ss4.service.InstructorService;

import java.util.List;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true,"Instructor created successfully!"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllInstructors(){
        return ResponseEntity.ok(new ApiResponse<>(true,"Instructor list successfully!", instructorService.findAllInstructors()));
    }
}
