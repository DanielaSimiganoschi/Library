package org.internship.library.controller;

import org.internship.library.entity.Author;
import org.internship.library.entity.Genre;
import org.internship.library.repository.GenreRepository;
import org.internship.library.service.AuthorService;
import org.internship.library.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/genre")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Genre>> getAllAuthors(){
        List<Genre> genres = genreService.findAllAGenres();
        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Genre> getAuthorById(@PathVariable("id") Long id){
        Genre genre = genreService.findGenreById(id);
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Genre> addGenre(@RequestBody Genre genre){
        Genre newGenre = genreService.addGenre(genre);
        return new ResponseEntity<>(newGenre, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Genre> updateGenre(@RequestBody Genre genre){
        Genre updateGenre = genreService.updateGenre(genre);
        return new ResponseEntity<>(updateGenre, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGenreById(@PathVariable("id") Long id){
        genreService.deleteAGenre(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
