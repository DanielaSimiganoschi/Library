package org.internship.library.repository;

import org.internship.library.entity.Genre;
import org.internship.library.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository  extends JpaRepository<Genre, Long> {
}
