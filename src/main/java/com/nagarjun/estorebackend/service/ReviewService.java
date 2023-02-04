package com.nagarjun.estorebackend.service;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import com.nagarjun.estorebackend.entity.Review;

public interface ReviewService {

    Review getReview(Long reviewId);
    List<Review> getReviewsByUsername(String username);

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    List<Review> getReviewsByProductId(Long productId);

    Review createReview(Review review, Long userId, Long productId);

    @PreAuthorize("hasRole('ADMIN') OR (hasRole('USER') AND @accessService.hasAccessToReview(#reviewId, principal.userId))")
    Review updateReview(Long reviewId, Review review);

    @PreAuthorize("hasRole('ADMIN') OR (hasRole('USER') AND @accessService.hasAccessToReview(#reviewId, principal.userId))")
    void deleteReview(Long reviewId);

    @PreAuthorize("hasRole('ADMIN')")
    List<Review> getReviews();

}
