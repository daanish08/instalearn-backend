package com.ford.controller;

import com.ford.TokenUtil.JwtUtil;
import com.ford.dto.AuthenticationRequest;
import com.ford.respository.IAdminRepository;
import com.ford.respository.IUserRepository;
import com.ford.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AppUserDetailsService userDetailsService;

    @Autowired
    private IAdminRepository adminRepository;

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/login")
    public Map<String, String> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        System.out.println(authenticationRequest);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
//        System.out.println(userDetails.getAuthorities().stream().toList().stream().findFirst().toString());
//        String r = userDetails.getAuthorities().stream().toList().stream().findFirst().toString();
        String role = getRoleForUser(authenticationRequest.getEmail());
        System.out.println("email : " + userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername(), role);

        Map<String, String> response = new HashMap<>();
        response.put("jwt", jwt);
        return response;
    }

    private String getRoleForUser(String username) {
        if (adminRepository.findByEmail(username) != null) {
            return "ADMIN";
        } else if (userRepository.findByEmail(username) != null) {
            return "USER";
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
