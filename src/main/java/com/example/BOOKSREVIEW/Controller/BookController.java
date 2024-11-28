package com.example.BOOKSREVIEW.Controller;

import com.example.BOOKSREVIEW.Data.BookData;
import com.example.BOOKSREVIEW.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class BookController {

    @Autowired
    private BookService bookService;

    // Retrieve all book reviews
    @GetMapping
    public List<BookData> getAllReviews() {
        List<BookData> reviews = bookService.getAllReviews();
        if (reviews.isEmpty()) {
            System.out.println("No reviews found");
        }
        return reviews;
    }

    // Create a new book review
    @PostMapping
    public BookData createReview(@RequestBody BookData bookReview) {
        return bookService.createReview(bookReview); // Create new review using service
    }

    // Update an existing book review
    @PutMapping("/{id}")
    public ResponseEntity<BookData> updateReview(@PathVariable Long id, @RequestBody BookData updatedReview) {
        BookData updatedBook = bookService.updateReview(id, updatedReview);
        return updatedBook != null ? ResponseEntity.ok(updatedBook) : ResponseEntity.notFound().build();
    }

    // Delete a specific book review
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        boolean isDeleted = bookService.deleteReview(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
