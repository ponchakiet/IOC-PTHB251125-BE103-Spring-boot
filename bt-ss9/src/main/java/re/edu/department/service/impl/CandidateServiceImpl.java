package re.edu.department.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import re.edu.department.dto.request.CandidateApplyDTO;
import re.edu.department.dto.response.CandidateResponse;
import re.edu.department.exception.DuplicateResourceException;
import re.edu.department.exception.InvalidFileException;
import re.edu.department.mapper.CandidateMapper;
import re.edu.department.model.Candidate;
import re.edu.department.repositories.ICandidateRepository;
import re.edu.department.service.ICandidateService;
import re.edu.department.validation.PdfValidator;

import java.io.IOException;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class CandidateServiceImpl implements ICandidateService {
    private final ICandidateRepository repository;
    private final CandidateMapper mapper;
    private final Cloudinary cloudinary;

    @Override
    public CandidateResponse apply(CandidateApplyDTO dto) {
        if (repository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Email đã tồn tại");
        }

        MultipartFile file = dto.getCvFile();
        PdfValidator.validate(file);

        try {
            Map<?, ?> uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "resource_type", "raw",
                            "folder", "cv"
                    )
            );

            String cvUrl = uploadResult.get("secure_url").toString();
            Candidate candidate = mapper.mapToEntity(dto, cvUrl);
            candidate.setCvUrl(cvUrl);
            Candidate saved = repository.save(candidate);
            return mapper.mapToResponse(saved);

        } catch (IOException e) {
            throw new InvalidFileException("Upload CV thất bại");
        }
    }
}

