package com.maitreya.reviewms.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // ADD A NEW REVIEW FOR A COMPANY
    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody Review review) {
        boolean isReviewAdded = reviewService.addReview(companyId, review);
        if (isReviewAdded)
            return new ResponseEntity<>("Review added successfully!", HttpStatus.CREATED);

        return new ResponseEntity<>("Company not found!", HttpStatus.NOT_FOUND);
    }

    // GET ALL REVIEWS FOR A COMPANY
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {
        List<Review> reviews = reviewService.getAllReviews(companyId);
        if (!reviews.isEmpty())
            return new ResponseEntity<>(reviews, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // GET A SPECIFIC REVIEW FOR A COMPANY
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) {
        Review review = reviewService.getReviewById(reviewId);
        if (review != null)
            return new ResponseEntity<>(review, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // UPDATE A SPECIFIC REVIEW FOR A COMPANY
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReviewById(@PathVariable Long reviewId,
                                                   @RequestBody Review updatedReview) {
        boolean isReviewUpdated = reviewService.updateReviewById(reviewId, updatedReview);
        if (isReviewUpdated)
            return new ResponseEntity<>("Review updated successfully!", HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE A SPECIFIC REVIEW FOR A COMPANY
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long reviewId) {
        boolean isReviewDeleted = reviewService.deleteReviewById(reviewId);
        if (isReviewDeleted)
            return new ResponseEntity<>("Review deleted successfully!", HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
