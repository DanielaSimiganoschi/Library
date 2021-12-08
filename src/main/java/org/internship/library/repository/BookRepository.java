package org.internship.library.repository;

import org.internship.library.entity.Book;
import org.internship.library.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository  extends JpaRepository<Book, Long> {

    void deleteBookById(Long id);

    Optional<Book> findBookById(Long id);

    Optional<Book> findByTitle(String title);

    @Query(value ="SELECT BOOK_ID FROM BOOKS_GENRES where GENRE_ID = ?1",nativeQuery = true)
     Optional<List<Long>> findBooksIDSByGenre(Long id);

    @Query(value ="SELECT * FROM BOOK where TITLE like %?1%",nativeQuery = true)
    Optional<List<Book>> findBooksByTitle(String title);

    @Query(value ="SELECT * FROM BOOK where ID = ?1",nativeQuery = true)
    Book findBookByGenre(Long id);

    @Query(value ="SELECT * FROM BOOK where AUTHOR_ID = ?1",nativeQuery = true)
    Optional<List<Book>> findBookByAuthor(Long id);
}
