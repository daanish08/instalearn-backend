package com.ford.service;

import com.ford.entity.Admin;
import com.ford.entity.Attachments;
import com.ford.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICourseService{
    ResponseEntity<List<Course>> findCoursesByAdmin(int adminId);
    ResponseEntity<List<Course>> getAllCourses();
    ResponseEntity<Course> findCourseByCourseId(int courseId);
}
