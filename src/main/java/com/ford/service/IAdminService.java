package com.ford.service;

import com.ford.entity.Admin;
import com.ford.entity.Course;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface IAdminService {
    public ResponseEntity<String> addAdmin(Admin admin);
    public ResponseEntity<String> deleteAdmin(int id);
    public ResponseEntity<List<Admin>> getAllAdmin();

    public ResponseEntity<String> createCourse( int admin,Course course);
    public ResponseEntity<String> deleteCourse(int admin,int courseId);
    public ResponseEntity<String> updateCourse(int admin,int courseId,Course course);

}
