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

    @Column(name ="BOOK_ID")
    private Long book_id;

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

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }
}
