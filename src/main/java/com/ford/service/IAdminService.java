package com.ford.service;

import com.ford.entity.Admin;
import com.ford.entity.Course;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;


public interface IAdminService {

    public ResponseEntity<Map<String,Object>> addAdmin(Admin admin);
    public ResponseEntity<String> deleteAdmin(int id);
    public ResponseEntity<List<Admin>> getAllAdmin();

    public ResponseEntity<String> createCourse( long admin,Course course);
    public ResponseEntity<String> deleteCourse(long admin,int courseId);
    public ResponseEntity<String> updateCourse(long admin,int courseId,Course course);

}
