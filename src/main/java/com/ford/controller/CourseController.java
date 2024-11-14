package com.ford.controller;

import com.ford.entity.Course;
import com.ford.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("api/v1/course")
public class CourseController {

    @Autowired
    CourseServiceImpl courseService;

    //http://localhost:8080/instalearn/api/v1/course/list
    @GetMapping("/list")
    public ResponseEntity<List<Course>> listCourses() {
        return courseService.getAllCourses();
    }

    //http://localhost:8080/instalearn/api/v1/course/{courseId}
    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseDetails(@PathVariable int courseId) {
        return courseService.findCourseByCourseId(courseId);
    }

    //http://localhost:8080/instalearn/api/v1/course/A1/count
    @GetMapping("A{adminId}/count")
    public ResponseEntity<Long> getCoursesCount(@PathVariable long adminId) {
        Long count = courseService.getCourseCount(adminId); // Ensure this returns a Long
        return ResponseEntity.ok(count);
    }

    //http://localhost:8080/instalearn/api/v1/course/count
    @GetMapping("/count")
    public ResponseEntity<Integer> getCoursesCount() {
        int count = courseService.getTotalCourseCount(); // Ensure this returns a Long
        return ResponseEntity.ok(count);
    }
}
