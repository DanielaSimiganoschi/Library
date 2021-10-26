package org.internship.library.repository;

import org.internship.library.entity.ISBN;
import org.internship.library.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISBNRepository  extends JpaRepository<ISBN, Long> {
}
