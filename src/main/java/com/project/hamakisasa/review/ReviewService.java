package com.project.hamakisasa.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, Review reviewDetails) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        review.setRating(reviewDetails.getRating());
        review.setComment(reviewDetails.getComment());
        review.setTenant(reviewDetails.getTenant());
        review.setProperty(reviewDetails.getProperty());

        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    public List<Review> getReviewsByTenant(Long tenantId) {
        return reviewRepository.findByTenant_Id(tenantId);
    }

    public List<Review> getReviewsByProperty(Long propertyId) {
        return reviewRepository.findByProperty_Id(propertyId);
    }
}

