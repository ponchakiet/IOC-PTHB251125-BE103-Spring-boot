package poncha.kiet.btss7.controller;

import org.springframework.web.bind.annotation.*;
import poncha.kiet.btss7.dto.CandidateCreateDTO;
import poncha.kiet.btss7.dto.CandidateUpdateDTO;
import poncha.kiet.btss7.entity.Candidate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import poncha.kiet.btss7.service.CandidateService;

@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;

    @PostMapping
    public ResponseEntity<Candidate> addCandidate(@Valid @RequestBody CandidateCreateDTO dto){
        Candidate candidate = candidateService.createCandidate(dto);
        return ResponseEntity.ok().body(candidate);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Candidate>  updateCandidate(@PathVariable Long id, @Valid @ModelAttribute CandidateUpdateDTO dto){
        Candidate candidate = candidateService.updateCandidateInfo(id, dto);
        return ResponseEntity.ok().body(candidate);
    }
}
