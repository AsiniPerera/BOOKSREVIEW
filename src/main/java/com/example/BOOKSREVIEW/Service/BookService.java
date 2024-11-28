package com.example.BOOKSREVIEW.Service;

import com.example.BOOKSREVIEW.Data.BookData;
import com.example.BOOKSREVIEW.Data.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Get all reviews
    public List<BookData> getAllReviews() {
        return bookRepository.findAll(); // Corrected the method to use the instance
    }

    // Get reviews by rating
    public List<BookData> getReviewsByRating(int rating) {
        return bookRepository.findByRating(rating); // Use findByRating method in repository
    }

    // Create a new review
    public BookData createReview(BookData bookReview) {
        bookReview.setDateAdded(LocalDate.now()); // Set the date when the review is added
        return bookRepository.save(bookReview); // Corrected the method to use the instance
    }

    // Update an existing review
    public BookData updateReview(Long id, BookData updatedReview) {
        Optional<BookData> existingReview = bookRepository.findById(id);

        // Check if the review exists
        if (existingReview.isPresent()) {
            BookData review = existingReview.get();
            review.setTitle(updatedReview.getTitle());
            review.setAuthor(updatedReview.getAuthor());
            review.setRating(updatedReview.getRating());
            review.setReviewText(updatedReview.getReviewText());
            return bookRepository.save(review); // Save and return the updated review
        }

        return null; // Return null or throw an exception if the review does not exist
    }

    // Delete a review by ID
    public boolean deleteReview(Long id) {
        Optional<BookData> existingReview = bookRepository.findById(id);

        // Check if the review exists
        if (existingReview.isPresent()) {
            bookRepository.deleteById(id); // Delete the review from the repository
            return true; // Return true if deletion was successful
        }
        return false; // Return false if review does not exist
    }
}
