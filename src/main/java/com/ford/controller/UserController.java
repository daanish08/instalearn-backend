package com.ford.controller;


import com.ford.entity.Admin;
import com.ford.entity.Course;
import com.ford.entity.User;
import com.ford.respository.IUserRepository;
import com.ford.service.CourseServiceImpl;
import com.ford.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/user")
public class UserController {

   @Autowired
   UserServiceImpl userService;

    //Creation of User Data
    //http://localhost:8080/instalearn/user/add
    @PostMapping("/add")
    public ResponseEntity<Map<String,Object>> addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    //Deletion of User Data
    //http://localhost:8080/instalearn/user/U{userId}/delete
    @DeleteMapping("/U{userId}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable int userId){
        return userService.deleteUser(userId);
    }

    //Update of User Data
    //http://localhost:8080/instalearn/user/U{userId}/edit
    @PutMapping("/U{userId}/edit")
    public ResponseEntity<String> updateUser(@PathVariable int userId,@RequestBody User user){
        return userService.updateUser(user,userId);
    }

    //http://localhost:8080/instalearn/user/userList/1
    @GetMapping("/userList/{userId}")
    public ResponseEntity<User> getUserListById(@PathVariable int userId){
        return userService.getUserById(userId);
    }





    // User Data by id
    //http://localhost:8080/instalearn/user/U{userId}/list
//    @GetMapping("/user/1")
//    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
//        Optional<User> user = userService.getUserById(id);
//
//        if (user.isPresent()) {
//            return ResponseEntity.ok(user.get()); // Returns 200 OK with the user data
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Returns 404 Not Found
//        }
//    }

    }




