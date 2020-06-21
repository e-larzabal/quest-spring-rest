package com.example.restlibrary.model;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;
    @Column(name="author")
    private String author;
    @Column(name="description")
    private String description;

    public Book() { }

    public Book(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Long getId() { return id; }

    public String getTitle() { return title; }

    public String getAuthor() { return author; }

    public String getDescription() { return description; }


    public void setId(Long id) { this.id = id; }

    public void setTitle(String title) { this.title = title; }

    public void setAuthor(String author) { this.author = author; }

    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
