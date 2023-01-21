package com.nagarjun.estorebackend.service;

import java.util.List;
import com.nagarjun.estorebackend.entity.Review;

public interface ReviewService {

    Review getReview(Long reviewId);
    List<Review> getReviewsByUsername(String username);
    List<Review> getReviewsByProductId(Long productId);
    Review createReview(Review review, Long userId, Long productId);
    Review updateReview(Long reviewId, Review review);
    void deleteReview(Long reviewId);
    List<Review> getReviews();

}
