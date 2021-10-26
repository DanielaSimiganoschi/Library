package org.internship.library.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name="ID")
    private Long id;
    private String title;
    private Date publishedDate;
    private int quantity;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="book")
    @Column(name="BOOK_ID", nullable = false)
    private List<Genre> genres;

    @ManyToOne
    private Author author;

    @OneToMany(mappedBy = "book")
    private List<ISBN> ISBNs = new ArrayList<>();

    public Book(){

    }

    public Book(String title, Date publishedDate, int quantity) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<ISBN> getISBNs() {
        return Collections.unmodifiableList(ISBNs);
    }

    public void addISBN(ISBN code){
        ISBNs.add(code);
    }

    public List<Genre> getGenres() {
        return genres ;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void addGenre(Genre genre){
        if(genres == null) genres = new ArrayList<>();
        genre.setBook(this);
        genres.add(genre);
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }
}
