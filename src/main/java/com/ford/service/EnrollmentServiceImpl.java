package com.ford.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ford.dto.EnrollmentDTO;
import com.ford.entity.*;
import com.ford.exception.CourseNotFoundException;
import com.ford.exception.DataNotFoundException;
import com.ford.respository.ICourseRepository;
import com.ford.respository.IEnrollmentRepository;
import com.ford.respository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
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

    private final ObjectMapper objectMapper;

    public EnrollmentServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

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
            enrollment.setStatus(EnrollmentStatus.APPROVED);
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

//    public ResponseEntity<List<Enrollment>> getPendingEnrollments(int adminId) {
//        try {
//            // Retrieve pending enrollments for the given admin
//            List<Enrollment> pendingEnrollments = (adminId);
//            System.out.println(pendingEnrollments);
//            if (pendingEnrollments.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            // Iterate over enrollments and check for null Course
//            for (Enrollment enrollment : pendingEnrollments) {
//                Course course = enrollment.getCourse();
//                if (course == null) {
//                    // Log or handle the case where course is null
//                    System.err.println("Enrollment with ID " + enrollment.getEnrollmentId() + " has no associated Course.");
//                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//                }
//                if (course.getAdmin() == null) {
//                    // Log or handle the case where admin is null
//                    System.err.println("Course with ID " + course.getCourseId() + " has no associated Admin.");
//                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//                }
//                // For logging or debugging purposes
//                System.out.println(course.getAdmin().getAdminId());
//            }
//
//            // Return the list of pending enrollments with an OK status
//            return new ResponseEntity<>(pendingEnrollments, HttpStatus.OK);
//
//        } catch (Exception e) {
//            // Log the exception
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @Transactional(readOnly = true)
//    public List<Enrollment> findPendingEnrollmentsByAdminId(long adminId) {
//        try {
//            // Spring Data JPA will automatically generate the JPQL query
//            return enrollmentRepository.findAll()
//                    .stream()
//                    .filter(e ->( e.getCourse().getAdmin().getAdminId()==(adminId)) && e.getStatus().equals("PENDING"))
//                    .toList();
//        } catch (Exception e) {
//            // Log the exception appropriately
//            throw new RuntimeException("Error fetching pending enrollments: " + e.getMessage(), e);
//        }
//    }

    @Transactional(readOnly = true)
    public List<Enrollment> findPendingEnrollmentsByAdminId(long adminId) {
        try {
            return enrollmentRepository.findPendingEnrollmentsByAdminId(adminId);
        } catch (Exception e) {
            // Log the exception appropriately
            throw new RuntimeException("Error fetching pending enrollments: " + e.getMessage(), e);
        }
    }

    public ResponseEntity<String> approveEnrollment(int enrollmentId, long adminId) {
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

    public ResponseEntity<String> rejectEnrollment(int enrollmentId, long adminId) {
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
                // reject the enrollment
                enrollment.setStatus(EnrollmentStatus.REJECTED); // Assuming there's a status field
                enrollmentRepository.save(enrollment);
            }
            return new ResponseEntity<>("Enrollment rejected successfully", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace(); // Consider using a logger instead of printStackTrace
            return new ResponseEntity<>("An error occurred while approving the enrollment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public long getEnrolledCountByUserId(int userId) {
        return enrollmentRepository.countEnrollmentsByUserId(userId);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

     private EnrollmentDTO mapToEnrollmentDTO(Enrollment enrollment) {
        System.out.println(enrollment);
        return new EnrollmentDTO(
                enrollment.getEnrollmentId(),
                enrollment.getCourse().getCourseName(),
                enrollment.getStatus().toString(),
                enrollment.getStatus());
    }


//    public ResponseEntity<String> findDetailsByUserId(@PathVariable int userId) {
//        try {
//            List<EnrollmentDTO> enrollments = enrollmentRepository.findEnrollmentDetailsByUserId(userId);
//
//            if (enrollments.isEmpty()) {
//                return ResponseEntity.notFound().build();
//            }
//
//            // Map Enrollment entities to EnrollmentDTO objects
//            List<EnrollmentDTO> enrollmentDTOs = enrollments.stream()
//                    .map(enrollment -> new EnrollmentDTO(
//                            enrollment.getEnrollmentId(), // Assuming enrollmentId is the ID
//                            enrollment.getUser().getUserName(), // Access username from User entity
//                            enrollment.getCourse().getCourseName(), // Access courseTitle from Course entity
//                            enrollment.getStatus().name() // Get the name of the EnrollmentStatus enum
//                    ))
//                    .collect(Collectors.toList());
//
//            // Serialize the list of DTOs to JSON
//            String jsonResponse = objectMapper.writeValueAsString(enrollmentDTOs);
//            return ResponseEntity.ok(jsonResponse);
//
//        } catch (JsonProcessingException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error serializing enrollment details.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("An error occurred while fetching enrollment details.");
//        }
//    }



}

