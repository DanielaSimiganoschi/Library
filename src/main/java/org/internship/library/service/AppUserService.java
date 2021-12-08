package org.internship.library.service;

import org.internship.library.entity.AppUser;
import org.internship.library.entity.Role;

import java.util.List;
import java.util.Optional;

public interface AppUserService {

    AppUser saveUser(AppUser appUser);

    Role saveRole(Role role);

    AppUser updateUser(AppUser appUser);

    AppUser getUser(String username);

    AppUser findUserById(Long id);

   AppUser findUserByUsername(String username);

   List<AppUser> findUsersByRole(Long id);

    void deleteUserById(Long id);

    List<AppUser> getUsers();

    List<Role> getRoles();
}
