package com.bookstore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Title is mandatory")
    @Size(min = 15, max = 150, message = "Title characters should be between 15 and 150")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "Author is mandatory")
    @Size(min = 5, max = 60, message = "Author characters should between 6 and 60")
    private String author;

    @Positive(message = "Price should be greater than 0")
    private double price;

    @Column(length = 1000)
    private String description;

    public Book() {}

    public Book(String title, String author, double price, String description) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.description = description;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
