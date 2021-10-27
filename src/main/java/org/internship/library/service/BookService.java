package org.internship.library.service;

import org.internship.library.entity.Author;
import org.internship.library.entity.Book;
import org.internship.library.entity.Genre;
import org.internship.library.entity.Patron;
import org.internship.library.exception.UserNotFoundException;
import org.internship.library.repository.AuthorRepository;
import org.internship.library.repository.BookRepository;
import org.internship.library.repository.GenreRepository;
import org.internship.library.repository.ISBNRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;


@Service
public class BookService {

    private final BookRepository bookRepo;
    private final ISBNRepository ISBNRepo;
    private final GenreRepository genreRepo;

    @Autowired
    public BookService(BookRepository bookRepo, GenreRepository genreRepository, AuthorRepository authorRepo, ISBNRepository isbnRepo, GenreRepository genreRepo) {
        this.bookRepo = bookRepo;
        ISBNRepo = isbnRepo;
        this.genreRepo = genreRepo;
    }

    public Book addBook(Book book){

        Book newBook = bookRepo.save(book);
        return newBook;
    }
    public void addGenre(List<Genre> genres){
        for (Genre genre:genres) {
            genreRepo.save(genre);
        }
    }

    public List<Book> findAllBooks(){
        return bookRepo.findAll();
    }

    public Book updateBook(Book book){
        return bookRepo.save(book);
    }

    public void deleteABook(Long id){
        bookRepo.deleteBookById(id);
    }

    public Book findABookById(Long id){
        return bookRepo.findBookById(id).orElseThrow(() -> new UserNotFoundException("Book by id "+ id + " was not found"));
    }


}