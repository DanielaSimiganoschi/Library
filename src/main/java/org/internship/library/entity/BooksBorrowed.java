package org.internship.library.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class BooksBorrowed implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private Date dateBorrowed;
    private Date toBeReturned;
    private boolean returnedOnTime;
    private boolean returned;

    @Column(name ="PATRON_ID")
    private Long patron_id;

    public BooksBorrowed(){

    }

    public BooksBorrowed(String ISBN, Date dateBorrowed, Date toBeReturned, boolean returnedOnTime, boolean returned) {
        this.isbn = ISBN;
        this.dateBorrowed = dateBorrowed;
        this.toBeReturned = toBeReturned;
        this.returnedOnTime = returnedOnTime;
        this.returned = returned;
    }

    public String getISBN() {
        return isbn;
    }

    public void setISBN(String ISBN) {
        this.isbn = ISBN;
    }

    public Date getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(Date dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public Date getToBeReturned() {
        return toBeReturned;
    }

    public void setToBeReturned(Date toBeReturned) {
        this.toBeReturned = toBeReturned;
    }

    public boolean getReturnedOnTime() {
        return returnedOnTime;
    }

    public void setReturnedOnTime(boolean returnedOnTime) {
        this.returnedOnTime = returnedOnTime;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public Long getPatron_id() {
        return patron_id;
    }

    public void setPatron_id(Long patron_id) {
        this.patron_id = patron_id;
    }

    public Long getId() {
        return id;
    }
}
