package org.internship.library.controller;


import org.internship.library.entity.AppUser;
import org.internship.library.entity.Author;
import org.internship.library.entity.Role;
import org.internship.library.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getAppUsers(){
        return ResponseEntity.ok().body(appUserService.getUsers());
    }

    @PostMapping("/users/add")
    public ResponseEntity<AppUser> addUser(@RequestBody AppUser appUser){
        AppUser newUser = appUserService.saveUser(appUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/role/add")
    public ResponseEntity<Role> addUser(@RequestBody Role role){
        Role newRole = appUserService.saveRole(role);
        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
    }

    @PostMapping("/user/addRole")
    public ResponseEntity<Role> addRoleToUser(@RequestBody RoleToUser form){
        appUserService.addRoleToAppUser(form.getUsername(),form.getRoleName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

class RoleToUser{
    private String username;
    private String roleName;

    public RoleToUser() {
    }

    public RoleToUser(String username, String roleName) {
        this.username = username;
        this.roleName = roleName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}