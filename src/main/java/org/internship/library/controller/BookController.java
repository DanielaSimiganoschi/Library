package org.internship.library.controller;


import org.internship.library.entity.Author;
import org.internship.library.entity.Book;
import org.internship.library.entity.Genre;
import org.internship.library.entity.ISBN;
import org.internship.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/library/books")
@Transactional
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
        Book newBook = bookService.addBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id){
        Book book = bookService.findABookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/findByTitle/{title}")
    public ResponseEntity<List<Book>> getBooksByTitle(@PathVariable("title") String title){
        List<Book> books = bookService.findBooksByTitle(title);
        return new ResponseEntity<>(books, HttpStatus.OK);
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


    @GetMapping("/filter")
    public ResponseEntity<List<Book>> filterBooks(@RequestParam(name ="idAuthor",required = false) Long idAuthor, @RequestParam(name ="idGenre", required = false) Long idGenre,
                                                  @RequestParam(name ="title", required = false) String title){
        List<Book> booksFilteredByAuthor= new ArrayList<>();
        List<Book> booksFilteredByGenre = new ArrayList<>();
        List<Book> booksFilteredByTitle = new ArrayList<>();
        List<Book> intersect1 = new ArrayList<>();
        List<Book> intersect = new ArrayList<>();

        if(idAuthor != null) {
            booksFilteredByAuthor = bookService.findBooksByAuthor(idAuthor);
        }
        if(idGenre != null) {
            booksFilteredByGenre = bookService.findBooksByGenre(idGenre);
        }

        if(title != null) {
            booksFilteredByTitle = bookService.findBooksByTitle(title);
        }

        if(idAuthor != null && idGenre != null && title != null) {
            intersect1 = booksFilteredByAuthor.stream()
                    .filter(booksFilteredByGenre::contains)
                    .collect(Collectors.toList());
            intersect =  intersect1.stream()
                    .filter(booksFilteredByTitle::contains)
                    .collect(Collectors.toList());

        } else  if(idAuthor != null && idGenre != null){
            intersect = booksFilteredByAuthor.stream()
                    .filter(booksFilteredByGenre::contains)
                    .collect(Collectors.toList());
        } else if(idAuthor != null && title != null){
            intersect = booksFilteredByAuthor.stream()
                    .filter(booksFilteredByTitle::contains)
                    .collect(Collectors.toList());
        } else if(idGenre != null && title != null ){
            intersect = booksFilteredByGenre.stream()
                    .filter(booksFilteredByTitle::contains)
                    .collect(Collectors.toList());
        } else if(idGenre != null){
            intersect= booksFilteredByGenre;
        }else if(idAuthor != null){
            intersect= booksFilteredByAuthor;
        }else if(title != null){
            intersect= booksFilteredByTitle;
        }

        return new ResponseEntity<>(intersect, HttpStatus.OK);
    }

    @GetMapping("/getISBNSForBookID/{id}")
    public ResponseEntity<List<ISBN>> getISBNSForBookID(@PathVariable("id") Long id){
        List<ISBN> isbns = bookService.findISBNsForBookID(id);
        return new ResponseEntity<>(isbns, HttpStatus.OK);
    }

    @GetMapping("/getISBNForISBNCode/{ISBN}")
    public ResponseEntity<ISBN> getISBNSForBookID(@PathVariable("ISBN") String ISBN){
       ISBN isbn = bookService.findISBNByISBN(ISBN);
        return new ResponseEntity<>(isbn, HttpStatus.OK);
    }

}
