package org.internship.library.repository;

import org.internship.library.entity.AppUser;
import org.internship.library.entity.Author;
import org.internship.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    void deleteUserById(Long id);

    AppUser findByUsername(String username);

    Optional<AppUser> findUserById(Long id);

    @Query(value ="SELECT * FROM APP_USER where ROLE_ID = ?1",nativeQuery = true)
    List<AppUser> findUsersByRole(Long id);
}
