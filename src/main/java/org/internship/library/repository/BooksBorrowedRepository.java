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

    @Query(value ="SELECT b FROM BOOKS_BORROWED b where b.book_id = ?1",nativeQuery = true)
    Optional<List<BooksBorrowed>> findBooksBorrowedForPatron(Long id);
}
