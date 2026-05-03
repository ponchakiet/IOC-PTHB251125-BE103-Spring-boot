package re.edu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.edu.dto.request.ReviewRequest;
import re.edu.dto.response.ReviewResponse;
import re.edu.entity.Product;
import re.edu.entity.Review;
import re.edu.entity.User;
import re.edu.repository.ProductRepository;
import re.edu.repository.ReviewRepository;
import re.edu.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ReviewResponse addReview(String email, ReviewRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + email));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + request.getProductId()));

        if (!reviewRepository.hasPurchasedProduct(user, product)) {
            throw new IllegalStateException("You can only review products you have purchased and received.");
        }

        if (reviewRepository.existsByUserAndProduct(user, product)) {
            throw new IllegalStateException("You have already reviewed this product.");
        }

        Review review = Review.builder()
                .user(user)
                .product(product)
                .rating(request.getRating())
                .comment(request.getComment())
                .createdDate(LocalDateTime.now())
                .build();

        return toResponse(reviewRepository.save(review));
    }

    public List<ReviewResponse> getReviewsByProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));

        return reviewRepository.findByProduct(product).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private ReviewResponse toResponse(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .userEmail(review.getUser().getEmail())
                .productId(review.getProduct().getId())
                .rating(review.getRating())
                .comment(review.getComment())
                .createdDate(review.getCreatedDate())
                .build();
    }
}
