package org.internship.library.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class BooksBorrowed implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Long ISBN;
    private Date dateBorrowed;
    private Date toBeReturned;
    private boolean returnedOnTime;
    private boolean returned;

    @ManyToOne
    private Patron patron;

    public BooksBorrowed(){

    }

    public BooksBorrowed(Long ISBN, Date dateBorrowed, Date toBeReturned, boolean returnedOnTime, boolean returned) {
        this.ISBN = ISBN;
        this.dateBorrowed = dateBorrowed;
        this.toBeReturned = toBeReturned;
        this.returnedOnTime = returnedOnTime;
        this.returned = returned;
    }

    public Long getISBN() {
        return ISBN;
    }

    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
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

    public boolean isReturnedOnTime() {
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

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public Long getId() {
        return id;
    }
}
