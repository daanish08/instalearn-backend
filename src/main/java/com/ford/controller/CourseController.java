package com.ford.controller;

import com.ford.entity.Course;
import com.ford.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/course")
public class CourseController {

    @Autowired
    CourseServiceImpl courseService;

    @GetMapping("/list")
    public ResponseEntity<List<Course>> listCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseDetails(@PathVariable int courseId){
        return courseService.findCourseByCourseId(courseId);
    }



}
