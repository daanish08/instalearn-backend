package com.ford.controller;


import com.ford.entity.Course;
import com.ford.entity.User;
import com.ford.service.CourseServiceImpl;
import com.ford.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {
//    @Autowired
//    AdminServiceImpl adminService;
//
    @Autowired
    CourseServiceImpl courseService;

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





}
