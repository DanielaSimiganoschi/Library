package org.internship.library.service;

import org.internship.library.entity.BooksBorrowed;
import org.internship.library.exception.UserNotFoundException;
import org.internship.library.repository.BookRepository;
import org.internship.library.repository.BooksBorrowedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class BooksBorrowedService {

    private final BooksBorrowedRepository booksBorrowedRepository;

    @Autowired
    public BooksBorrowedService(BooksBorrowedRepository booksBorrowedRepository) {
        this.booksBorrowedRepository= booksBorrowedRepository;
    }

    public List<BooksBorrowed> findAllBooksBorrowed(){
        return booksBorrowedRepository.findAll();
    }

    public BooksBorrowed addBookBorrowed(BooksBorrowed bookBorrowed){
        return booksBorrowedRepository.save(bookBorrowed);
    }

    public BooksBorrowed updateBookBorrowed(BooksBorrowed bookBorrowed){
        return booksBorrowedRepository.save(bookBorrowed);
    }

    public void deleteABookBorrowed(Long id){
        booksBorrowedRepository.deleteById(id);
    }

    public BooksBorrowed findABookBorrowedById(Long id){
        return booksBorrowedRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Patron by id "+ id + " was not found"));
    }



}
