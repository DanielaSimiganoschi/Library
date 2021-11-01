package org.internship.library.repository;

import org.internship.library.entity.BooksBorrowed;
import org.internship.library.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface BooksBorrowedRepository  extends JpaRepository<BooksBorrowed, Long> {

    @Query(value ="SELECT * FROM BOOKS_BORROWED where PATRON_ID = ?1",nativeQuery = true)
    Optional<List<BooksBorrowed>> findBooksBorrowedForPatron(Long id);

    @Query(value ="SELECT * FROM BOOKS_BORROWED where RETURNED = 0 and PATRON_ID = ?1",nativeQuery = true)
    Optional<List<BooksBorrowed>> findBooksBorrowedForPatronNotReturned(Long id);

    @Query(value ="SELECT * FROM BOOKS_BORROWED where RETURNED_ON_TIME = 1 and PATRON_ID = ?1",nativeQuery = true)
    Optional<List<BooksBorrowed>> findBooksBorrowedForPatronReturnedOnTime(Long id);

    @Query(value ="DELETE * FROM BOOKS_BORROWED where PATRON_ID = ?1",nativeQuery = true)
    void deleteBooksBorrowedForPatron(Long id);

}
