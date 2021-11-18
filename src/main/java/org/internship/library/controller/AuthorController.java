package org.internship.library.controller;

import org.internship.library.entity.Author;
import org.internship.library.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Author>> getAllAuthors(){
        List<Author> authors = authorService.findAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") Long id){
        Author author = authorService.findAuthorById(id);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

     @PostMapping("/add")
     public ResponseEntity<Author> addAuthor(@RequestBody Author author){
         Author newAuthor = authorService.addAuthor(author);
         return new ResponseEntity<>(newAuthor, HttpStatus.CREATED);
     }

    @PutMapping("/update")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author){
        Author updateAuthor = authorService.updateAuthor(author);
        return new ResponseEntity<>(updateAuthor, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAuthorById(@PathVariable("id") Long id){
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
