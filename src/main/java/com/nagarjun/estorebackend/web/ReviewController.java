package com.nagarjun.estorebackend.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nagarjun.estorebackend.entity.Review;
import com.nagarjun.estorebackend.service.ReviewService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId) {

        return new ResponseEntity<>(reviewService.getReview(reviewId), HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getReviewsByProductId(@PathVariable Long productId) {

        return new ResponseEntity<>(reviewService.getReviewsByProductId(productId), HttpStatus.OK);
    }    

    @PostMapping("/user/{userId}/product/{productId}")
    public ResponseEntity<HttpStatus> createReview(@PathVariable Long productId, @PathVariable Long userId, @RequestBody Review review) {

        reviewService.createReview(review, userId, productId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long reviewId, @RequestBody Review review) {

        return new ResponseEntity<>(reviewService.updateReview(reviewId, review), HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<HttpStatus> deleteReview(@PathVariable Long reviewId) {

        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
