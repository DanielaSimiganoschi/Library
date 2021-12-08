package org.internship.library.service;

import org.internship.library.entity.Author;
import org.internship.library.exception.UserNotFoundException;
import org.internship.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepo;

    @Autowired
    public AuthorService(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }

    public Author addAuthor(Author author){
       return authorRepo.save(author);
    }

    public List<Author> findAllAuthors(){
        return authorRepo.findAll();
    }

    public Author updateAuthor(Author author){
     return authorRepo.save(author);
    }


    public void deleteAuthorById(Long id){
        authorRepo.deleteAuthorById(id);
    }

    public Author findAuthorById(Long id){
        return authorRepo.findAuthorById(id).orElseThrow(() -> new UserNotFoundException("Author by id "+ id + " was not found"));
    }
}
