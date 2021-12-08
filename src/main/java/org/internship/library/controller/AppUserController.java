package org.internship.library.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.internship.library.entity.AppUser;
import org.internship.library.entity.Author;
import org.internship.library.entity.Book;
import org.internship.library.entity.Role;
import org.internship.library.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("library")
@CrossOrigin("http://localhost:4200")

public class AppUserController {

    @Autowired
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<AppUser>> getAppUsers(){
        return ResponseEntity.ok().body(appUserService.getUsers());
    }

    @GetMapping("/roles/all")
    public ResponseEntity<List<Role>> getAllRoles(){
        return ResponseEntity.ok().body(appUserService.getRoles());
    }

    @GetMapping("/users/find/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable("id") Long id){
        AppUser user = appUserService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users/findByUsername/{username}")
    public ResponseEntity<AppUser> getUserByUsername(@PathVariable("username") String username){
        AppUser user = appUserService.findUserByUsername(username);
        return new ResponseEntity<AppUser>(user, HttpStatus.OK);
    }

    @GetMapping("/users/findUsersByRole/{id}")
    public ResponseEntity<List<AppUser>> getBooksByTitle(@PathVariable("id") Long id){
        List<AppUser> users = appUserService.findUsersByRole(id);
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/users/add")
    public ResponseEntity<AppUser> addUser(@RequestBody AppUser appUser){
        AppUser newUser = appUserService.saveUser(appUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id){
        appUserService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/users/update")
    public ResponseEntity<AppUser> updateUser(@RequestBody AppUser user){
        AppUser updateUser = appUserService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }


    @PostMapping("/roles/add")
    public ResponseEntity<Role> addUser(@RequestBody Role role){
        Role newRole = appUserService.saveRole(role);
        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                AppUser appUser = appUserService.getUser(username);
                List<String> roles = Arrays.asList(appUser.getRole()).stream().map(role -> role.getName()).collect(Collectors.toList());
                String access_token = JWT.create()
                        .withSubject(appUser.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 1*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",roles)
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token",access_token);
                tokens.put("refresh_token",refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                // response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error",exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);
            }
        } else{
            throw new RuntimeException("Refresh token is missing");
        }

}
}

