package org.internship.library.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class Patron implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int score;
    private int nrBooksAllowed;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="PATRON_ID")
    private List<BooksBorrowed> books = new ArrayList<>();

    public Patron(){

    }

    public Patron(String firstName, String lastName, String phoneNumber, int score, int nrBooksAllowed) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.score = score;
        this.nrBooksAllowed = nrBooksAllowed;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNrBooksAllowed() {
        return nrBooksAllowed;
    }

    public void setNrBooksAllowed(int nrBooksAllowed) {
        this.nrBooksAllowed = nrBooksAllowed;
    }


    public List<BooksBorrowed> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public void addBook(BooksBorrowed book){
        books.add(book);
    }

    public Long getId() {
        return id;
    }
}
