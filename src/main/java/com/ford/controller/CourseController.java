package com.ford.controller;

import com.ford.entity.Course;
import com.ford.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api/v1/course")
public class CourseController {

    @Autowired
    CourseServiceImpl courseService;

    //http://localhost:8080/instalearn/api/v1/course/list
    @GetMapping("/list")
    public ResponseEntity<List<Course>> listCourses(){
        return courseService.getAllCourses();
    }

    //http://localhost:8080/instalearn/api/v1/course/C{courseId}
    @GetMapping("/C{courseId}")
    public ResponseEntity<Course> getCourseDetails(@PathVariable int courseId){
        return courseService.findCourseByCourseId(courseId);
    }

    //http://localhost:8080/instalearn/api/v1/course/count
    @GetMapping("/count")
    public ResponseEntity<Long> getCoursesCount(){
        Long count = courseService.getCourseCount(); // Ensure this returns a Long
        return ResponseEntity.ok(count);
    }


//    @GetMapping("/approved")
//    public ResponseEntity<List<Course>> getApprovedCourses(@RequestParam int userId) {
//        List<Course> courses = courseService.getApprovedCoursesForUser(userId);
//        return ResponseEntity.ok(courses);
//    }



}
