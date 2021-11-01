package org.internship.library.service;

import org.internship.library.entity.Author;
import org.internship.library.entity.BooksBorrowed;
import org.internship.library.entity.Patron;
import org.internship.library.exception.UserNotFoundException;
import org.internship.library.repository.BooksBorrowedRepository;
import org.internship.library.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PatronService {

    private final PatronRepository patronRepo;
    private final BooksBorrowedRepository booksBorrowedRepo;

    @Autowired
    public PatronService(PatronRepository patronRepo, BooksBorrowedRepository booksBorrowedRepo) {
        this.patronRepo = patronRepo;
        this.booksBorrowedRepo = booksBorrowedRepo;
    }


    public Patron addPatron(Patron patron){
        return patronRepo.save(patron);
    }

    public List<Patron> findAllPatrons(){
        return patronRepo.findAll();
    }

    public Patron updatePatron(Patron patron){
        return patronRepo.save(patron);
    }

    public void deleteAPatron(Long id){
        deleteBooksBorrowedForPatronId(id);
        patronRepo.deletePatronById(id);
    }

    public Patron findAPatronById(Long id){
        return patronRepo.findPatronById(id).orElseThrow(() -> new UserNotFoundException("Patron by id "+ id + " was not found"));
    }



    public List<BooksBorrowed> findAllBooksBorrowedForPatronId(Long id){
        return booksBorrowedRepo.findBooksBorrowedForPatron(id).orElseThrow(() -> new UserNotFoundException("Books borrowed by Patron id "+ id + " were not found"));
    }

    public List<BooksBorrowed> findBooksNotReturnedForPatronId(Long id){
        return booksBorrowedRepo.findBooksBorrowedForPatronNotReturned(id).orElseThrow(() -> new UserNotFoundException("Patron id "+ id + " Returned all books"));
    }

    public List<BooksBorrowed> findBooksReturnedOnTimeForPatronId(Long id){
        return booksBorrowedRepo.findBooksBorrowedForPatronReturnedOnTime(id).orElseThrow(() -> new UserNotFoundException("Patron id "+ id + " Returned all books"));
    }


    public void deleteBooksBorrowedForPatronId(Long id){
        booksBorrowedRepo.deleteBooksBorrowedForPatron(id);
    }


}
