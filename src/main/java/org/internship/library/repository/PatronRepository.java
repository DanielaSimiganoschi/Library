package org.internship.library.repository;

import org.internship.library.entity.Book;
import org.internship.library.entity.BooksBorrowed;
import org.internship.library.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PatronRepository extends JpaRepository<Patron, Long> {
    void deletePatronById(Long id);

    Optional<Patron> findPatronById(Long id);

    @Query(value ="SELECT * FROM PATRON where FIRST_NAME like %?1%",nativeQuery = true)
    Optional<List<Patron>> findPatronByFirstName(String firstName);

    @Query(value ="SELECT * FROM PATRON where LAST_NAME like %?1%",nativeQuery = true)
    Optional<List<Patron>> findPatronByLastName(String lastName);

}
