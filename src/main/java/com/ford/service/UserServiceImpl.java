package com.ford.service;

import com.ford.entity.Admin;
import com.ford.entity.User;
import com.ford.entity.User;
import com.ford.respository.ICourseRepository;
import com.ford.respository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    IUserRepository userRepository;
    @Autowired
    private ICourseRepository iCourseRepository;

    @Override
    public ResponseEntity<Map<String,Object>> addUser(User user) {
        User newUser = userRepository.save(user);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User added successfully");
        response.put("user", newUser); // Assuming 'admin' is the object you want to return
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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

    public ResponseEntity<User> getUserById(int userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body( userRepository.findByUserId(userId));
    }

//    public Optional<User> getUserById(Integer id) {
//        return userRepository.findByUserId(id);
//    }

//    public ResponseEntity<List<User>> listByUserId(int id) {
//        return ResponseEntity.status(HttpStatus.CREATED).body( userRepository.findAllByUserId(id);
//    }




    public Long getUsersCount() {
        return userRepository.count(); // count() returns a long
    }


    @Override
    public ResponseEntity<String> updateUser(User user, int id) {
        Optional<User> existingUserOptional = userRepository.findById(id);

        if (existingUserOptional.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        User updatedUser = existingUserOptional.get();
        // Correctly set the values using setter methods
        updatedUser.setUserName(user.getUserName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPhone(user.getPhone());
        updatedUser.setPassword(user.getPassword());

        userRepository.save(updatedUser);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

}
