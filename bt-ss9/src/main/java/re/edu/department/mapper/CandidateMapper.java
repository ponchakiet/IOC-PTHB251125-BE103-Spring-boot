package re.edu.department.mapper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import re.edu.department.dto.request.CandidateApplyDTO;
import re.edu.department.dto.response.CandidateResponse;
import re.edu.department.model.Candidate;

@Component
public class CandidateMapper {
  public static CandidateResponse mapToResponse(Candidate entity) {
    CandidateResponse response = new CandidateResponse();
    response.setId(entity.getId());
    response.setName(entity.getName());
    response.setEmail(entity.getEmail());
    response.setCvUrl(entity.getCvUrl());
    return response;
  }
  public static Candidate mapToEntity(CandidateApplyDTO dto, String cvUrl) {
      Candidate entity = new Candidate();
      entity.setName(dto.getName());
      entity.setEmail(dto.getEmail());
      entity.setCvUrl(cvUrl);
      return entity;
  }

}
