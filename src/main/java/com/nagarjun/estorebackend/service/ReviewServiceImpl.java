package com.nagarjun.estorebackend.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nagarjun.estorebackend.Constants;
import com.nagarjun.estorebackend.entity.Product;
import com.nagarjun.estorebackend.entity.Review;
import com.nagarjun.estorebackend.entity.User;
import com.nagarjun.estorebackend.exception.ProductNotFoundException;
import com.nagarjun.estorebackend.exception.ResourceNotFoundException;
import com.nagarjun.estorebackend.repository.ProductRepository;
import com.nagarjun.estorebackend.repository.ReviewRepository;
import com.nagarjun.estorebackend.repository.UserRepository;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Review getReview(Long reviewId) {

        Optional<Review> reviewEntity = reviewRepository.findById(reviewId);
        if(reviewEntity.isEmpty()) throw new ResourceNotFoundException(reviewId, Constants.REVIEW);
        return reviewEntity.get();
    }
    
    @Override
    public List<Review> getReviewsByProductId(Long productId) {
        
        Optional<Product> productEntity = productRepository.findById(productId);
        if(productEntity.isEmpty()) throw new ProductNotFoundException(productId);
        List<Review> reviews = reviewRepository.findByProductId(productId).get();
        if(reviews.isEmpty()) throw new ResourceNotFoundException(productId, Constants.PRODUCT);
        return reviews;
    }    

    @Override
    public Review createReview(Review review, Long userId, Long productId) {
        // TODO Auto-generated method stub
        User user = userRepository.findById(userId).get();
        Product product = productRepository.findById(productId).get();
        review.setProduct(product);
        review.setUser(user);
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Long reviewId, Review review) {

        Optional<Review> reviewEntity = reviewRepository.findById(reviewId);
        if(reviewEntity.isEmpty()) throw new ResourceNotFoundException(reviewId, Constants.REVIEW);
        Review updateReview  = reviewEntity.get();
        updateReview.setDescription(review.getDescription());
        updateReview.setRating(review.getRating());
        updateReview.setTitle(review.getTitle());
        return updateReview;
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);        
    }

    @Override
    public List<Review> getReviews() {

        return (List<Review>)reviewRepository.findAll();
    }

    @Override
    public List<Review> getReviewsByUsername(String username) {
        // TODO Auto-generated method stub
        return null;
    }

}
