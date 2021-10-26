package org.internship.library.repository;

import org.internship.library.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatronRepository extends JpaRepository<Patron, Long> {
    void deletePatronById(Long id);

   Optional<Patron> findPatronById(Long id);
}
