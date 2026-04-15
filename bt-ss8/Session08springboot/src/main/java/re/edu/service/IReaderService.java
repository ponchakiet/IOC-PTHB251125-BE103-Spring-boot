package re.edu.service;

import re.edu.dto.request.ReaderCreate;
import re.edu.dto.response.ReaderResponse;

public interface IReaderService {
    ReaderResponse createReader(ReaderCreate dto);
}
