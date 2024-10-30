package com.ford.service;

import com.ford.entity.*;
import com.ford.exception.CourseNotFoundException;
import com.ford.exception.DataNotFoundException;
import com.ford.exception.EnrollmentNotFoundException;
import com.ford.respository.ICourseRepository;
import com.ford.respository.IEnrollmentRepository;
import com.ford.respository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentServiceImpl implements IEnrollmentService{
    @Autowired
    private IEnrollmentRepository enrollmentRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ICourseRepository courseRepository;

    @Override
    public ResponseEntity<String> addEnrollment(int courseId, int userId) {
        try {
            // 1. Retrieve the Course and User
            Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException(HttpStatus.NOT_FOUND, "Course not found"));
            User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException(HttpStatus.NOT_FOUND, "User not found")); // Assuming you have a User entity and repository

            // 2. Create a new Enrollment
            Enrollment enrollment = new Enrollment();
            enrollment.setCourse(course);
            enrollment.setUser(user);
            enrollment.setEnrollmentDate(LocalDateTime.now());
            enrollment.setStatus(EnrollmentStatus.PENDING);
            // 3. Save the Enrollment
            enrollmentRepository.save(enrollment);
            return ResponseEntity.status(HttpStatus.CREATED).body("Enrollment added successfully");
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage()); //Handle specific exceptions
        } catch (Exception e) {
            // Log the exception properly (e.g., using a logging framework)
            e.printStackTrace(); //Replace with proper logging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    public ResponseEntity<List<Enrollment>> getPendingEnrollments(int adminId) {
        try {
            // Retrieve pending enrollments for the given admin
            Enrollment enrollment = new Enrollment();
            System.out.println(enrollment);
            System.out.println(enrollment.getCourse().getAdmin().getAdminId());
            List<Enrollment> pendingEnrollments = enrollmentRepository.findPendingEnrollmentsByAdminId(adminId);

            // Check if any pending enrollments were found
            if (pendingEnrollments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            // Return the list of pending enrollments with an OK status
            return new ResponseEntity<>(pendingEnrollments, HttpStatus.OK);

        } catch (Exception e) {
            // Log the exception (consider using a logging framework)
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<String> approveEnrollment(int enrollmentId, int adminId) {
        try {
            // Fetch the enrollment
            Optional<Enrollment> enrollmentOptional = enrollmentRepository.findById(enrollmentId);
            if (!enrollmentOptional.isPresent()) {
                return new ResponseEntity<>("Enrollment not found", HttpStatus.NOT_FOUND);
            }

            Enrollment enrollment = enrollmentOptional.get();

            // Fetch the course associated with the enrollment
            Course course = enrollment.getCourse();
            if (course == null) {
                return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
            }

            // Fetch the admin associated with the course
            Admin courseAdmin = course.getAdmin();
            if (courseAdmin == null || courseAdmin.getAdminId() != adminId) {
                return new ResponseEntity<>("Admin is not authorized to approve this enrollment", HttpStatus.UNAUTHORIZED);
            }

            else{
            // Approve the enrollment
            enrollment.setStatus(EnrollmentStatus.APPROVED); // Assuming there's a status field
            enrollmentRepository.save(enrollment);
            }

            return new ResponseEntity<>("Enrollment approved successfully", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace(); // Consider using a logger instead of printStackTrace
            return new ResponseEntity<>("An error occurred while approving the enrollment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> rejectEnrollment(int enrollmentId, int adminId) {
        try {
            // Fetch the enrollment
            Optional<Enrollment> enrollmentOptional = enrollmentRepository.findById(enrollmentId);
            if (!enrollmentOptional.isPresent()) {
                return new ResponseEntity<>("Enrollment not found", HttpStatus.NOT_FOUND);
            }

            Enrollment enrollment = enrollmentOptional.get();

            // Fetch the course associated with the enrollment
            Course course = enrollment.getCourse();
            if (course == null) {
                return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
            }

            // Fetch the admin associated with the course
            Admin courseAdmin = course.getAdmin();
            if (courseAdmin == null || courseAdmin.getAdminId() != adminId) {
                return new ResponseEntity<>("Admin is not authorized to approve this enrollment", HttpStatus.UNAUTHORIZED);
            }

            else{
                // Approve the enrollment
                enrollment.setStatus(EnrollmentStatus.REJECTED); // Assuming there's a status field
                enrollmentRepository.save(enrollment);
            }

            return new ResponseEntity<>("Enrollment rejected successfully", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace(); // Consider using a logger instead of printStackTrace
            return new ResponseEntity<>("An error occurred while approving the enrollment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

