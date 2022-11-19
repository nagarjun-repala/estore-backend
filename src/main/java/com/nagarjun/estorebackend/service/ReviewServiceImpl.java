package com.nagarjun.estorebackend.service;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarjun.estorebackend.entity.Review;
import com.nagarjun.estorebackend.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review getReview(Long reviewId) {

        Optional<Review> getReview = reviewRepository.findById(reviewId);
        return unwrapReview(getReview, reviewId);
    }    

    @Override
    public Review createReview(Review review) {
        
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Long reviewId, Review review) {

        Optional<Review> getReview = reviewRepository.findById(reviewId);
        Review unwrappedReview = unwrapReview(getReview, reviewId);
        unwrappedReview.setDescription(review.getDescription());
        unwrappedReview.setRating(review.getRating());
        unwrappedReview.setTitle(review.getTitle());
        return unwrappedReview;
    }

    @Override
    public void deleteReview(Long reviewId) {
        
        reviewRepository.deleteById(reviewId);        
    }

    // @Override
    // public Review addReviewToProduct(Long productId) {
    //     // TODO Auto-generated method stub
    //     return null;
    // }

    static Review unwrapReview(Optional<Review> entity, Long id) {

        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException();

    }

}
