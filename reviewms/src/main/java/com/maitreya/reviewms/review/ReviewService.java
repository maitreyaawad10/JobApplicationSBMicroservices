package com.maitreya.reviewms.review;

import java.util.List;

public interface ReviewService {
    boolean addReview(Long companyId, Review review);

    List<Review> getAllReviews(Long companyId);

    Review getReviewById(Long reviewId);

    boolean updateReviewById(Long reviewId, Review updatedReview);

    boolean deleteReviewById(Long reviewId);
}
