package org.internship.library.repository;

import org.internship.library.entity.ISBN;
import org.internship.library.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ISBNRepository  extends JpaRepository<ISBN, Long> {

    //Optional<ISBN> updateAISBNByISBN(String isbn);

    @Query(value ="SELECT * FROM ISBN where BOOK_ID = ?1",nativeQuery = true)
    Optional<List<ISBN>> findISBNSForBookID(Long id);

    @Query(value ="SELECT * FROM ISBN where ISBN = ?1",nativeQuery = true)
    Optional<ISBN> findISBNbyISBN(String ISBN);
}
