package org.internship.library.repository;

import org.internship.library.entity.Author;
import org.internship.library.entity.Genre;
import org.internship.library.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository  extends JpaRepository<Genre, Long> {

    void deleteGenreById(Long id);

    Optional<Genre> findAGenreById(Long id);

}
