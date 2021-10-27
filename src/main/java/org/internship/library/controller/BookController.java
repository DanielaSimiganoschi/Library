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
        List<Genre> listGenres = new ArrayList<Genre>();
        for (Genre genre: book.getGenres()) {
            listGenres.add(genre);
        }
        Book newBook = new Book(book.getTitle(), book.getPublishedDate(), book.getQuantity());
        newBook.setAuthor(book.getAuthor());
        //newBook.setGenres(listGenres);

        Book newBook2 = bookService.addBook(newBook);
        for (Genre genre: listGenres) {
            genre.setBook_id(newBook2.getId());
        }
        bookService.addGenre(listGenres);
        return new ResponseEntity<>(newBook2, HttpStatus.CREATED);
    }

}