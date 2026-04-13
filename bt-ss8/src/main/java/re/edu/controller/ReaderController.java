package re.edu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import re.edu.dto.request.ReaderCreate;
import re.edu.dto.response.ReaderResponse;
import re.edu.service.IReaderService;

@RestController
@RequestMapping("/api/readers")
@RequiredArgsConstructor
public class ReaderController {
    private final IReaderService readerService;

    @PostMapping
    public ResponseEntity<ReaderResponse> createReader(
            @ModelAttribute @Valid ReaderCreate request
    ) {
        ReaderResponse response = readerService.createReader(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
