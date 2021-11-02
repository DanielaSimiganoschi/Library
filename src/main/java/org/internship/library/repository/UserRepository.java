package org.internship.library.repository;

import org.internship.library.entity.UserLibrary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserLibrary,Long> {
    UserLibrary findByUserName(String username);
}
