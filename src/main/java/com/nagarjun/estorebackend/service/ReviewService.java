package com.nagarjun.estorebackend.service;

import com.nagarjun.estorebackend.entity.Review;

public interface ReviewService {

    Review getReview(Long reviewId);
    Review createReview(Review review);
    Review updateReview(Long reviewId, Review review);
    void deleteReview(Long reviewId);
    // Review addReviewToProduct(Long productId);

}
