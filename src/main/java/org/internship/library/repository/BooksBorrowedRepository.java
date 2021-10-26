package org.internship.library.repository;

import org.internship.library.entity.BooksBorrowed;
import org.internship.library.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksBorrowedRepository  extends JpaRepository<BooksBorrowed, Long> {
}
