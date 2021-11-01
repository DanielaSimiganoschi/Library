package org.internship.library.service;

import org.internship.library.entity.*;
import org.internship.library.exception.UserNotFoundException;
import org.internship.library.repository.AuthorRepository;
import org.internship.library.repository.BookRepository;
import org.internship.library.repository.GenreRepository;
import org.internship.library.repository.ISBNRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLOutput;
import java.util.*;


@Service
@Transactional
public class BookService {

    private final BookRepository bookRepo;
    private final ISBNRepository ISBNRepo;

    @Autowired
    public BookService(BookRepository bookRepo, GenreRepository genreRepository, AuthorRepository authorRepo, ISBNRepository isbnRepo) {
        this.bookRepo = bookRepo;
        this.ISBNRepo = isbnRepo;
    }

    public Book addBook(Book book) {
        List<ISBN> isbns = book.getISBNs();
        for (ISBN isbn : isbns
        ) {
            isbn.setBorrowed(false);

        }
        book.setISBNs(isbns);
        Book newBook = bookRepo.save(book);
        return newBook;
    }


    public List<Book> findAllBooks() {
        return bookRepo.findAll();
    }

    public Book updateBook(Book book) {
        return bookRepo.save(book);
    }

    public void deleteABook(Long id) {
        bookRepo.deleteBookById(id);
    }

    public Book findABookById(Long id) {
        return bookRepo.findBookById(id).orElseThrow(() -> new UserNotFoundException("Book by id " + id + " was not found"));
    }

    public List<Book> findBooksByGenre(Long id) {
        List<Long> idsBooks = bookRepo.findBooksIDSByGenre(id).orElseThrow(() -> new UserNotFoundException("Books with genre ID " + id + " were not found"));
        List<Book> books = new ArrayList<Book>();

        for (Long idBook : idsBooks) {
            books.add(bookRepo.findBookByGenre(idBook));
        }
        return books;
    }

    public List<Book> findBooksByAuthor(Long id) {
        return bookRepo.findBookByAuthor(id).orElseThrow(() -> new UserNotFoundException("Books with author ID " + id + " were not found"));
    }

}
