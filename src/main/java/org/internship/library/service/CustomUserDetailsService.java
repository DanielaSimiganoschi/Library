package org.internship.library.service;

import org.internship.library.entity.UserLibrary;
import org.internship.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserLibrary userLibrary = userRepository.findByUserName(username);
        return new org.springframework.security.core.userdetails.User(userLibrary.getUserName(), userLibrary.getPassword(), new ArrayList<>());
    }
}
