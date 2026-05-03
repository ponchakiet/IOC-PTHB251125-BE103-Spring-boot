package re.edu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.dto.EmployeeCreateDTO;
import re.edu.model.Employee;
import re.edu.service.EmployeeService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Employee> create(@ModelAttribute EmployeeCreateDTO dto) throws IOException {
        Employee saved = employeeService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
