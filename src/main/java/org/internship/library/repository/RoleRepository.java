package org.internship.library.repository;

import org.internship.library.entity.AppUser;
import org.internship.library.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
