package org.internship.library.entity;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.io.Serializable;

@Entity
public class Genre  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;

    @Column(name ="BOOK_ID")
    private Long book_id;


    public Genre(){

    }

    public Genre(String name) {
        this.name = name;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {return id;}


}
