package org.internship.library.controller;


import org.internship.library.entity.Author;
import org.internship.library.entity.Book;
import org.internship.library.entity.Genre;
import org.internship.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/library/books")
@Transactional
public class BookController {

  private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = bookService.findAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book newBook = bookService.addBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id){
        Book book = bookService.findABookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        Book updateBook = bookService.updateBook(book);
        return new ResponseEntity<>(updateBook, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable("id") Long id){
        bookService.deleteABook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/filterByGenre/{id}")
    public ResponseEntity<List<Book>> filterBooksByGenre(@PathVariable("id") Long id){
        List<Book> books = bookService.findBooksByGenre(id);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/filterByAuthor/{id}")
    public ResponseEntity<List<Book>> filterBooksByAuthor(@PathVariable("id") Long id){
        List<Book> books = bookService.findBooksByAuthor(id);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

}
