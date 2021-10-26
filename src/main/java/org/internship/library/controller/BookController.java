package org.internship.library.controller;


import org.internship.library.entity.Author;
import org.internship.library.entity.Book;
import org.internship.library.entity.Genre;
import org.internship.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/library/books")
public class BookController {

  private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = bookService.findAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book){

        Genre genre1 = new Genre("genre 1");
        Genre genre2 = new Genre("genre 2");
        List<Genre> listGenres = Arrays.asList(genre1,genre2);
        Book newBook = new Book("title 1", null, 1);
        newBook.setGenres(listGenres);
        Book newBook2 = bookService.addBook(newBook);
        return new ResponseEntity<>(newBook2, HttpStatus.CREATED);
    }

}
