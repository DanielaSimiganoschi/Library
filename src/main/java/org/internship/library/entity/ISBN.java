package org.internship.library.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ISBN implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ISBN;
    private boolean borrowed;

    @ManyToOne
    @JoinColumn(name="book_id", nullable=false)
    private Book book;

    public ISBN(){

    }

    public ISBN(String ISBN, boolean borrowed) {
        this.ISBN = ISBN;
        this.borrowed = borrowed;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
