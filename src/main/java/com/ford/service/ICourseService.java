package com.ford.service;

import com.ford.entity.Course;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICourseService{
    ResponseEntity<List<Course>> findCoursesByAdmin(long adminId);
    ResponseEntity<List<Course>> getAllCourses();
    ResponseEntity<Course> findCourseByCourseId(int courseId);
}
