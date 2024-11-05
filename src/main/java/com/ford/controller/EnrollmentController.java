package com.ford.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.ford.dto.EnrollmentDTO;
import com.ford.entity.Admin;
import com.ford.entity.Course;
import com.ford.entity.Enrollment;
import com.ford.service.CourseServiceImpl;
import com.ford.service.EnrollmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/")
public class EnrollmentController {

    @Autowired
    CourseServiceImpl courseService;

    @Autowired
    EnrollmentServiceImpl enrollmentService;

//    Creation of ENROLL Data
    //http://localhost:8080/instalearn/api/v1/course/U2/C2/enroll
    @PostMapping("U{userId}/C{courseId}/enroll")
    public ResponseEntity<String> EnrollNewCourse(@PathVariable int userId,@PathVariable int courseId){
        return enrollmentService.addEnrollment(courseId,userId);
    }


    //http://localhost:8080/instalearn/api/v1/2/enroll/count
    @GetMapping("{userId}/enroll/count")
    public ResponseEntity<Long> GetEnrolledCourseCount(@PathVariable int userId){
        System.out.println(userId);
        long count= enrollmentService.getEnrolledCountByUserId(userId);
        System.out.println(count);
        return ResponseEntity.ok(count);
    }

//    http://localhost:8080/instalearn/api/v1/enrollments
@GetMapping("/enrollments")
public ResponseEntity<List<EnrollmentDTO>> getEnrollments() {
    List<EnrollmentDTO> enrollments = enrollmentService.getAllEnrollments()
            .stream()
            .map(enrollment -> new EnrollmentDTO(
                    enrollment.getEnrollmentId(),
                    enrollment.getUser().getUserName(), // Assuming you have a user relationship
                    enrollment.getCourse().getCourseName(),  // Assuming you have a course relationship
                    enrollment.getStatus()
            ))
            .collect(Collectors.toList());
    return ResponseEntity.ok(enrollments);
}

    //http://localhost:8080/instalearn/api/v1/2/enroll/courses
//    @GetMapping("{userId}/enroll/courses")
//    public ResponseEntity<List<Enrollment>> GetEnrolledCourseDetails(@PathVariable int userId){
//        System.out.println(userId);
//
//        //errrororoororor
//        List<Enrollment> data= (List<Enrollment>) enrollmentService.findPendingEnrollmentsByAdminId(userId);
//        System.out.println(data);
//        return ResponseEntity.ok(data);
//    }






}
