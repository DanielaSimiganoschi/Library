package org.internship.library.controller;

import org.internship.library.entity.BooksBorrowed;
import org.internship.library.entity.Patron;
import org.internship.library.service.BooksBorrowedService;
import org.internship.library.service.PatronService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/patrons")
public class PatronController {

    private final PatronService patronService;
    private final BooksBorrowedService booksBorrowedService;

    public PatronController(PatronService patronService, BooksBorrowedService booksBorrowedService) {
        this.patronService = patronService;
        this.booksBorrowedService = booksBorrowedService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Patron>> getAllPatrons(){
        List<Patron> patrons = patronService.findAllPatrons();
        return new ResponseEntity<>(patrons, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Patron> addPatron(@RequestBody Patron patron){
        Patron newPatron = patronService.addPatron(patron);
        return new ResponseEntity<>(newPatron, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable("id") Long id){
        Patron patron = patronService.findAPatronById(id);
        return new ResponseEntity<>(patron, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Patron> updatePatron(@RequestBody Patron patron){
        Patron updatePatron = patronService.updatePatron(patron);
        return new ResponseEntity<>(updatePatron, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePatronById(@PathVariable("id") Long id){
        patronService.deleteAPatron(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findBooksBorrowed/{id}")
    public ResponseEntity<List<BooksBorrowed>> getAllBooksBorrowedForPatron(@PathVariable("id") Long id){
        List<BooksBorrowed> booksBorrowedByPatron = patronService.findAllBooksBorrowedForPatronId(id);
        return new ResponseEntity<>(booksBorrowedByPatron, HttpStatus.OK);
    }
}
