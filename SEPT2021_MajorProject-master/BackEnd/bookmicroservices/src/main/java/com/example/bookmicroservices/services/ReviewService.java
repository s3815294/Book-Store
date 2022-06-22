package com.example.bookmicroservices.services;

import com.example.bookmicroservices.Repositories.BookRepository;
import com.example.bookmicroservices.Repositories.ReviewRepository;
import com.example.bookmicroservices.exceptions.ReviewAlreadyExistsException;
import com.example.bookmicroservices.model.Book;
import com.example.bookmicroservices.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private BookRepository bookRepository;

    public Review saveReview (Review review) {
        try {
            Book bookToAdd = bookRepository.findBookById(review.getBookId());
            review.setBook(bookToAdd);
            return reviewRepository.save(review);
        } catch (Exception e) {
            throw new ReviewAlreadyExistsException("Review '" + review.getTitle() + "' already exists");
        }
    }
}
