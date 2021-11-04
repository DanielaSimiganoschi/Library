package org.internship.library.service;

import org.internship.library.entity.AppUser;
import org.internship.library.entity.Role;

import java.util.List;

public interface AppUserService {

    AppUser saveUser(AppUser appUser);

    Role saveRole(Role role);

    void addRoleToAppUser(String username, String roleName);

    AppUser getUser(String username);

    List<AppUser> getUsers();

}
