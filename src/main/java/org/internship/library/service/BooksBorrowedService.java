package org.internship.library.service;

import org.internship.library.entity.BooksBorrowed;
import org.internship.library.entity.ISBN;
import org.internship.library.entity.Patron;
import org.internship.library.exception.UserNotFoundException;
import org.internship.library.repository.BooksBorrowedRepository;
import org.internship.library.repository.ISBNRepository;
import org.internship.library.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BooksBorrowedService {

    private final BooksBorrowedRepository booksBorrowedRepository;
    private final PatronService patronService;
    private final PatronRepository patronRepository;
    private final ISBNRepository isbnRepository;
    private final BookService bookService;

    @Autowired
    public BooksBorrowedService(BooksBorrowedRepository booksBorrowedRepository, PatronService patronService, BookService bookService, PatronRepository patronRepository, ISBNRepository isbnRepository) {
        this.booksBorrowedRepository = booksBorrowedRepository;
        this.patronService = patronService;
        this.bookService = bookService;
        this.patronRepository = patronRepository;
        this.isbnRepository = isbnRepository;
    }

    public List<BooksBorrowed> findAllBooksBorrowed() {
        return booksBorrowedRepository.findAll();
    }

    public BooksBorrowed addBookBorrowed(Long id,BooksBorrowed bookBorrowed) {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 1);
        Date currentDatePlusOne = c.getTime();
        bookBorrowed.setDateBorrowed(date);
        bookBorrowed.setToBeReturned(currentDatePlusOne);
        bookBorrowed.setPatron_id(id);
        Patron patron = patronService.findAPatronById(bookBorrowed.getPatron_id());
        patron.setNrBooksAllowed(patron.getNrBooksAllowed()-1);
        patronRepository.save(patron);
        ISBN isbn = isbnRepository.findISBNbyISBN(bookBorrowed.getISBN()).orElseThrow(() -> new UserNotFoundException("ISBN not Found"));
        bookService.updateISBNT(isbn);
        return booksBorrowedRepository.save(bookBorrowed);
    }

    public BooksBorrowed updateBookBorrowed(BooksBorrowed bookBorrowed) {
        Patron patron = patronService.findAPatronById(bookBorrowed.getPatron_id());

        Date date = new Date();

        if(bookBorrowed.isReturned() && date.before(bookBorrowed.getToBeReturned())) {

            bookBorrowed.setReturnedOnTime(true);
            patron.setScore(patron.getScore() + 1);
            patron.setNrBooksAllowed(patron.getNrBooksAllowed() + 1);

            if (patron.getScore() % 3 == 0 && patron.getScore() != 0) {
                patron.setNrBooksAllowed(patron.getNrBooksAllowed() + 1);
                patron.setScore(patron.getScore() - 3);
            }

            ISBN isbn = isbnRepository.findISBNbyISBN(bookBorrowed.getISBN()).orElseThrow(() -> new UserNotFoundException("ISBN not Found"));
            bookService.updateISBNF(isbn);

        } else if(bookBorrowed.isReturned()){

            if(patron.getScore()>0) {
                patron.setScore(patron.getScore() - 1);
            }

            patron.setNrBooksAllowed(patron.getNrBooksAllowed()+1);
            ISBN isbn = isbnRepository.findISBNbyISBN(bookBorrowed.getISBN()).orElseThrow(() -> new UserNotFoundException("ISBN not Found"));
           bookService.updateISBNF(isbn);
        }

        patronService.updatePatron(patron);
        return booksBorrowedRepository.save(bookBorrowed);
    }

    public void deleteABookBorrowed(Long id) {
        booksBorrowedRepository.deleteById(id);
    }

    public BooksBorrowed findABookBorrowedById(Long id) {
        return booksBorrowedRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Patron by id " + id + " was not found"));
    }


}
