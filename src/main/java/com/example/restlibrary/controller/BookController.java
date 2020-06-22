package com.example.restlibrary.controller;

import com.example.restlibrary.model.Book;
import com.example.restlibrary.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;


@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // example: http://localhost:8080/books
    @GetMapping("/books")
    @ResponseBody
    public List<Book> getAll() {
        try {
            return bookRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Request HTTP : GET
    // Action DB : READ (SELECT)
    // Example resource : http://localhost:8080/book/1
    @GetMapping("/book/{id}")
    @ResponseBody
    public Book getBook(@PathVariable Long id) {
        try {
            Book book = new Book();
            if (id != null) {
                Optional<Book> optionalBook = bookRepository.findById(id);
                if (optionalBook.isPresent()) {
                    book = optionalBook.get();
                }
            }
            return book;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Request HTTP : POST
    // Action DB : SAVE (INSERT)
    // Example resource : http://localhost:8080/book/
    @PostMapping("/book")
    @ResponseBody
    public Book postBook(@ModelAttribute Book book) {
        try {
            return bookRepository.save(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Request HTTP : PUT
    // Action DB : UPDATE
    // Example resource : http://localhost:8080/books/2
    @PutMapping("/books/{id}")
    @ResponseBody
    public Book putBook(@PathVariable Long id, @ModelAttribute Book book){
        try {
            Book bookToUpdate = bookRepository.findById(id).get();
            bookToUpdate.setTitle(book.getTitle());
            bookToUpdate.setAuthor(book.getAuthor());
            bookToUpdate.setDescription(book.getDescription());
            return bookRepository.save(bookToUpdate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Request HTTP : DELETE
    // Action DB : DELETE
    // Example : http://localhost:8080/book/delete/2
    @DeleteMapping("/book/delete/{id}")
    public void deleteWizard(@PathVariable Long id) {
        try {
            bookRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Request HTTP : POST
    // Action DB : READ (SELECT)
    // Example : http://localhost:8080/books/search
    @PostMapping("/book/search")
    @ResponseBody
    public List<Book> search(@RequestBody Map<String, String> body){
        try {
            String searchTerm = body.get("text");
            return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
