package com.example.BOOKSREVIEW.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookData, Long> {

    // Custom query method to find books by rating
    List<BookData> findByRating(int rating);
}
