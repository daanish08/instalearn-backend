package com.ford.service;

import com.ford.entity.Admin;
import com.ford.entity.Course;
import com.ford.respository.IAdminRepository;
import com.ford.respository.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    IAdminRepository adminRepository;

    @Autowired
    ICourseRepository courseRepository;


    @Override
    public ResponseEntity<String> addAdmin(Admin admin) {
       try{
           Admin newAdmin = adminRepository.save(admin);
           return ResponseEntity.status(HttpStatus.CREATED).body(newAdmin.toString()+" ADDED SUCCESFULLY");
       }catch(Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
       }
    }

    @Override
    public ResponseEntity<String> deleteAdmin(int id) {
        try{
            System.out.println(id);
             adminRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(" DELETED SUCCESSFULLY");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<Admin>> getAllAdmin() {
        return ResponseEntity.status(HttpStatus.CREATED).body( adminRepository.findAll());
    }

    @Override
    public ResponseEntity<String> createCourse(long adminId, Course course) {
        try {
                Admin admin = adminRepository.findByAdminId(adminId);
                course.setAdmin(admin);
                courseRepository.save(course);
                System.out.println(course);
            return ResponseEntity.status(HttpStatus.CREATED).body("COURSE ADDED SUCCESSFULLY");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> deleteCourse(long adminId, int courseId) {
        // Retrieve the course by ID
        Optional<Course> existingCourse = courseRepository.findById(courseId);

        // Check if the course exists
        if (!existingCourse.isPresent()) {
            // Return a 404 Not Found response if the course does not exist
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }

        // Delete the course
        courseRepository.deleteById(courseId);

        // Return a success response
        return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateCourse(long admin, int courseId, Course course) {
        Optional<Course> existingCourse = courseRepository.findById(courseId);
        if (!existingCourse.isPresent()) {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }
        // Update the course details
        Course updatedCourse = existingCourse.get();
        updatedCourse.setCourseName(course.getCourseName());
        updatedCourse.setDescription(course.getDescription());
        updatedCourse.setInstructor(course.getInstructor());
        updatedCourse.setDuration(course.getDuration());
        updatedCourse.setCourseURL(course.getCourseURL());

        // Save the updated course
        courseRepository.save(updatedCourse);
        return new ResponseEntity<>("Course updated successfully", HttpStatus.OK);}

}
