package com.nagarjun.estorebackend.service;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarjun.estorebackend.entity.Product;
import com.nagarjun.estorebackend.entity.Review;
import com.nagarjun.estorebackend.entity.User;
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

        Optional<Review> getReview = reviewRepository.findById(reviewId);
        return unwrapReview(getReview, reviewId);
    }
    
    @Override
    public List<Review> getReviewsByProductId(Long productId) {

        return (List<Review>) reviewRepository.findByProductId(productId).get();
    }    

    @Override
    public Review createReview(Review review, Long userId, Long productId) {

        User user = userRepository.findById(userId).get();
        Product product = productRepository.findById(productId).get();
        review.setProduct(product);
        review.setUser(user);
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


    static Review unwrapReview(Optional<Review> entity, Long id) {

        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException();

    }

    @Override
    public List<Review> getReviews() {

        return (List<Review>)reviewRepository.findAll();
    }

}
