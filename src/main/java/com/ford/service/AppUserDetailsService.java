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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (adminRepository.findByEmail(email) != null) {
            System.out.println("admin " + adminRepository.findByEmail(email));
            return new User(email, "{noop}" + adminRepository.findByEmail(email).getPassword(), Collections.singletonList(() -> "ROLE_ADMIN"));
        } else if (userRepository.findByEmail(email) != null) {
            System.out.println("user " + userRepository.findByEmail(email));
            return new User(email, "{noop}" + userRepository.findByEmail(email).getPassword(), Collections.singletonList(() -> "ROLE_USER"));
        }
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
