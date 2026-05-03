package re.edu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import re.edu.dto.request.ReviewRequest;
import re.edu.dto.response.ReviewResponse;
import re.edu.service.ReviewService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/api/reviews")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ReviewResponse> addReview(@AuthenticationPrincipal UserDetails userDetails,
                                                    @Valid @RequestBody ReviewRequest request) {
        return ResponseEntity.ok(reviewService.addReview(userDetails.getUsername(), request));
    }

    @GetMapping("/api/products/{id}/reviews")
    public ResponseEntity<List<ReviewResponse>> getReviews(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewsByProduct(id));
    }
}
