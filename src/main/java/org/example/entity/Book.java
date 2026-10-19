package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Book_Table")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Book_Id")
    private int id;
    @Column(name = "Book_Title")
    private String title;
    @Column(name = "Book_Price")
    private double price;
    @Column(name = "Availability")
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "Author_ID")
    private Author author;

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

}
