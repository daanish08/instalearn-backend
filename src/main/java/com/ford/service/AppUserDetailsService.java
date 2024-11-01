package com.ford.service;


import com.ford.respository.IAdminRepository;
import com.ford.respository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IAdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (adminRepository.findByName(username) != null) {
            return new User(username, "{noop}" + adminRepository.findByName(username).getPassword(), Collections.singletonList(() -> "ROLE_ADMIN"));
        } else if (userRepository.findByUsername(username) != null) {
            return new User(username, "{noop}" + userRepository.findByUsername(username).getPassword(), Collections.singletonList(() -> "ROLE_USER"));
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
