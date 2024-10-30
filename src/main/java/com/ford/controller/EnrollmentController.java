package com.ford.controller;


import com.ford.entity.Admin;
import com.ford.service.CourseServiceImpl;
import com.ford.service.EnrollmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/course")
public class EnrollmentController {

    @Autowired
    CourseServiceImpl courseService;

    @Autowired
    EnrollmentServiceImpl enrollmentService;

    //Creation of Admin Data
//    @PostMapping("U{userId}/C{courseId}/enroll")
//    public ResponseEntity<String> EnrollNewCourse(@PathVariable int userId,@PathVariable int courseId){
//        return enrollmentService.EnrollCourse(userId,courseId);
//    }

}
