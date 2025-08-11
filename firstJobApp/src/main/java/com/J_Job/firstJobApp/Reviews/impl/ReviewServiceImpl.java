package com.J_Job.firstJobApp.Reviews.impl;

import com.J_Job.firstJobApp.Reviews.Review;
import com.J_Job.firstJobApp.Reviews.ReviewRepository;
import com.J_Job.firstJobApp.Reviews.ReviewService;
import com.J_Job.firstJobApp.company.Company;
import com.J_Job.firstJobApp.company.CompanyService;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

     private final ReviewRepository reviewRepository;
     private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository,CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService=companyService;
    }

    @Override
    public List<Review> getAllReviews(Long CompanyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(CompanyId);
        return  reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company!=null){
            review.setCompany(company);
             reviewRepository.save(review);
             return true;
        }
        else {
            return false;
        }
    }

}
