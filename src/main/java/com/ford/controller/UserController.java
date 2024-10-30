package com.ford.controller;


import com.ford.entity.Course;
import com.ford.entity.User;
import com.ford.service.CourseServiceImpl;
import com.ford.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //Creation of Admin Data
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user){
        return userService.addUser(user);
    }


    //Deletion of User Data
    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable int userId){
        return userService.deleteUser(userId);
    }

    //Updation of User Data
    @PutMapping("/{userId}/edit")
    public ResponseEntity<String> updateUser(@PathVariable int userId,@RequestBody User user){
        return userService.updateUser(user,userId);
    }





}
