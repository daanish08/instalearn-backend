package com.ford.service;

import com.ford.entity.Admin;
import com.ford.entity.User;
import com.ford.entity.User;
import com.ford.respository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    IUserRepository userRepository;
    
    @Override
    public ResponseEntity<String> addUser(User user) {
        try{
            User newUser = userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser.toString()+" ADDED NEW USER SUCCESFULLY");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> deleteUser(int id) {
        try{
            System.out.println(id);
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(" DELETED USER SUCCESSFULLY");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.CREATED).body( userRepository.findAll());
    }

    @Override
    public ResponseEntity<User> getUser(int id) {
        return null;
    }

    @Override
    public ResponseEntity<String> updateUser(User user, int id) {
        Optional<User> existingUserOptional = userRepository.findById(id);

        if (existingUserOptional.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        User updatedUser = existingUserOptional.get();
        // Correctly set the values using setter methods
        updatedUser.setUsername(user.getUsername());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());

        userRepository.save(updatedUser);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

}
