package com.ford.controller;


import com.ford.entity.Admin;
import com.ford.entity.Course;
import com.ford.entity.Enrollment;
import com.ford.service.CourseServiceImpl;
import com.ford.service.EnrollmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/course")
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



}
