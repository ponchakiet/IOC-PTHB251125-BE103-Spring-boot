package re.edu.department.service;

import re.edu.department.dto.request.CandidateApplyDTO;
import re.edu.department.dto.response.CandidateResponse;

public interface ICandidateService {
     CandidateResponse apply(CandidateApplyDTO dto) ;
}
