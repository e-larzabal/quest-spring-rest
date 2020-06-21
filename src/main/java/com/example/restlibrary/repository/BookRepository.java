package com.example.restlibrary.repository;

import com.example.restlibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // custom query to search to book post by title or content
    List<Book> findByTitleContainingOrDescriptionContaining(String text, String textAgain);

}
