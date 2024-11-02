package com.ford.service;

import com.ford.entity.Admin;
import com.ford.entity.Attachments;
import com.ford.entity.Course;
import com.ford.respository.IAdminRepository;
import com.ford.respository.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    IAdminRepository adminRepository;

    @Autowired
    ICourseRepository courseRepository;


    @Override
    @PostMapping("/addAdmin")
    public ResponseEntity<Map<String, Object>> addAdmin(@RequestBody Admin admin) {
        // Logic to add admin
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Admin added successfully");
        response.put("admin", admin); // Assuming 'admin' is the object you want to return

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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


    public ResponseEntity<Admin> getAdminById(long adminId) {
        return ResponseEntity.status(HttpStatus.CREATED).body( adminRepository.findByAdminId(adminId));
    }


//    @Transactional
//    public ResponseEntity<String> createCourse(long adminId, Course course) {
//        try {
//            Admin admin = adminRepository.findByAdminId(adminId);
//            if (admin == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
//            }
//
//            // Set the admin for the course
//            course.setAdmin(admin);
//
//            // Save the course, which will also save the attachments due to cascade settings
//            courseRepository.save(course);
//
//            return ResponseEntity.status(HttpStatus.CREATED).body("COURSE ADDED SUCCESSFULLY");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating course: " + e.getMessage());
//        }
//    }

    @Transactional
    public ResponseEntity<String> createCourse(long adminId, Course course) {
        System.out.println("Creating course with adminId: {}"+ adminId); // Add logging for adminId
        System.out.println("Course data:"+ course); // Log the course object itself

        try {
            Admin admin = adminRepository.findByAdminId(adminId);
            System.out.println(admin);
            if (admin == null) {
//                log.warn("Admin not found for adminId: {}", adminId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
            }
            course.setAdmin(admin);
            System.out.println("Saving course...");
            courseRepository.save(course); // Add logging before and after save
            System.out.println("Course saved successfully.");
            return ResponseEntity.status(HttpStatus.CREATED).body("COURSE ADDED SUCCESSFULLY");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Database error creating course: {}"+ e.getMessage()+ e); // Log the exception with stack trace
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Database error creating course: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error creating course: {}"+ e.getMessage()+ e); // Log the exception with stack trace
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating course: " + e.getMessage());
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
    public ResponseEntity<String> updateCourse(long adminId, int courseId, Course course) {
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
        updatedCourse.setGithubURL(course.getGithubURL());
        updatedCourse.setDriveURL(course.getDriveURL());

        // Save the updated course
        courseRepository.save(updatedCourse);
        return new ResponseEntity<>("Course updated successfully", HttpStatus.OK);}

}
