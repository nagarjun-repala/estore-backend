package com.nagarjun.estorebackend.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nagarjun.estorebackend.entity.Review;
import com.nagarjun.estorebackend.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/product")
public class ReviewController {

    ReviewService reviewService;

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId) {

        return new ResponseEntity<>(reviewService.getReview(reviewId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Review> createReview(Review review) {

        return new ResponseEntity<>(reviewService.createReview(review), HttpStatus.CREATED);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long reviewId, @RequestBody Review review) {

        return new ResponseEntity<>(reviewService.updateReview(reviewId, review), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteReview(@PathVariable Long reviewId) {

        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
