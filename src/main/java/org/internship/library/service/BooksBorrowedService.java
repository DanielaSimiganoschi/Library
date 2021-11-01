package org.internship.library.service;

import org.internship.library.entity.BooksBorrowed;
import org.internship.library.entity.Patron;
import org.internship.library.exception.UserNotFoundException;
import org.internship.library.repository.BooksBorrowedRepository;
import org.internship.library.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BooksBorrowedService {

    private final BooksBorrowedRepository booksBorrowedRepository;
    private final PatronService patronService;

    @Autowired
    public BooksBorrowedService(BooksBorrowedRepository booksBorrowedRepository, PatronService patronService) {
        this.booksBorrowedRepository = booksBorrowedRepository;
        this.patronService = patronService;
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
        return booksBorrowedRepository.save(bookBorrowed);
    }

    public BooksBorrowed updateBookBorrowed(BooksBorrowed bookBorrowed) {
        Patron patron = patronService.findAPatronById(bookBorrowed.getPatron_id());
        Date date = new Date();
        if(bookBorrowed.isReturned() && date.before(bookBorrowed.getToBeReturned())) {
            bookBorrowed.setReturnedOnTime(true);
            patron.setScore(patron.getScore() + 1);
            if (patron.getScore() % 3 == 0 && patron.getScore() != 0) {
                patron.setNrBooksAllowed(patron.getNrBooksAllowed() + 1);

            }
        } else if(bookBorrowed.isReturned()){
            patron.setScore(patron.getScore() - 1);
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
