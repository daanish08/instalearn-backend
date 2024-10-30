package com.ford.service;

import com.ford.entity.Admin;
import com.ford.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {

    public ResponseEntity<String> addUser(User user);
    public ResponseEntity<String> deleteUser(int id);
    public ResponseEntity<List<User>> getAllUsers();
    public ResponseEntity<User> getUser(int id);
    public ResponseEntity<String> updateUser(User user,int id);
}
