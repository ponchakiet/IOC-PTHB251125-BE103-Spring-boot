package poncha.kiet.btss7.service;

import org.springframework.beans.factory.annotation.Autowired;
import poncha.kiet.btss7.dto.CandidateCreateDTO;
import poncha.kiet.btss7.dto.CandidateUpdateDTO;
import poncha.kiet.btss7.entity.Candidate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import poncha.kiet.btss7.repository.ICandidateRepository;

@RequiredArgsConstructor
@Service
public class CandidateService {
    private final ICandidateRepository candidateRepository;

    @Transactional
    public Candidate createCandidate(CandidateCreateDTO dto) {
        Candidate candidate = Candidate.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .yearsOfExperience(dto.getYearsOfExperience())
                .phone(dto.getPhone())
                .build();
        return candidateRepository.save(candidate);
    }

    @Transactional
    public Candidate updateCandidateInfo(Long id, CandidateUpdateDTO dto) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy ứng viên với ID: " + id));

        candidate.setAddress(dto.getAddress());
        candidate.setBio(dto.getBio());

        return candidateRepository.save(candidate);
    }

}
