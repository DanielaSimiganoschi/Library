package org.internship.library.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Book.class)
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "ID")
    private Long id;
    private String title;
    private String publishedDate;
    private int quantity;
    private String description;

    @ManyToMany(cascade = {
            CascadeType.MERGE
    })
    @JoinTable(name = "Books_Genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres = new ArrayList<>();

    @ManyToOne
    private Author author;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "book")
    private List<ISBN> ISBNs = new ArrayList<>();

    public Book() {

    }

    public Book(String title, String publishedDate, int quantity, String description) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.quantity = quantity;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
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

    public void addISBN(ISBN code) {
        ISBNs.add(code);
    }

    public List<Genre> getGenres() {
        return genres;
    }


    public void setISBNs(List<ISBN> ISBNs) {
        this.ISBNs = ISBNs;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
