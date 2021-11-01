package org.internship.library.service;


import org.internship.library.entity.Author;
import org.internship.library.entity.Genre;
import org.internship.library.exception.UserNotFoundException;
import org.internship.library.repository.AuthorRepository;
import org.internship.library.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre addGenre(Genre genre){
        return genreRepository.save(genre);
    }

    public List<Genre> findAllAGenres(){
        return genreRepository.findAll();
    }

    public Genre updateGenre(Genre genre){
        return genreRepository.save(genre);
    }


    public void deleteAGenre(Long id){
        genreRepository.deleteGenreById(id);
    }

    public Genre findGenreById(Long id){
        return genreRepository.findAGenreById(id).orElseThrow(() -> new UserNotFoundException("Genre by id "+ id + " was not found"));
    }
}
