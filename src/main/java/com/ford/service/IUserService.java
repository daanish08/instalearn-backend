package com.ford.service;

import com.ford.entity.Admin;
import com.ford.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IUserService {

    public ResponseEntity<Map<String,Object>> addUser(User user);
    public ResponseEntity<String> deleteUser(int id);
    public ResponseEntity<List<User>> getAllUsers();
    public ResponseEntity<String> updateUser(User user,int id);
}
