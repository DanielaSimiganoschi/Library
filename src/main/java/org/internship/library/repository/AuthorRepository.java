package org.internship.library.repository;

import org.internship.library.entity.Author;
import org.internship.library.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository  extends JpaRepository<Author, Long> {

    void deleteAuthorById(Long id);

    Optional<Author> findAuthorById(Long id);
}
