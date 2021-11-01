package org.internship.library.controller;


import org.internship.library.entity.BooksBorrowed;
import org.internship.library.entity.Patron;
import org.internship.library.service.BooksBorrowedService;
import org.internship.library.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/booksBorrowed")
public class BooksBorrowedController {

    private final BooksBorrowedService booksBorrowedService;


    public BooksBorrowedController(BooksBorrowedService booksBorrowedService) {
        this.booksBorrowedService = booksBorrowedService;

    }

    @GetMapping("/all")
    public ResponseEntity<List<BooksBorrowed>> getAllBooksBorrowed(){
        List<BooksBorrowed> booksBorrowed = booksBorrowedService.findAllBooksBorrowed();
        return new ResponseEntity<>(booksBorrowed, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<BooksBorrowed> getBookBorrowedById(@PathVariable("id") Long id){
        BooksBorrowed bookBorrowed = booksBorrowedService.findABookBorrowedById(id);
        return new ResponseEntity<>(bookBorrowed, HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<BooksBorrowed> updateBookBorrowed(@RequestBody BooksBorrowed bookBorrowed){
        BooksBorrowed updateBookBorrowed = booksBorrowedService.updateBookBorrowed(bookBorrowed);

        return new ResponseEntity<>(updateBookBorrowed, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBookBorrowedById(@PathVariable("id") Long id){
        booksBorrowedService.deleteABookBorrowed(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
