package com.ford.service;

import com.ford.entity.Admin;
import com.ford.entity.Attachments;
import com.ford.entity.Enrollment;
import com.ford.respository.ICourseRepository;
import com.ford.respository.IEnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.ford.entity.Course;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private final ICourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public ResponseEntity<List<Course>> findCoursesByAdmin(long adminId) {
        List<Course> courses = courseRepository.findByAdmin_AdminId(adminId);
        if (courses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Course> findCourseByCourseId(int courseId) {
        Course course = courseRepository.findCourseByCourseId(courseId);
        if (course==null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        System.out.println(courses);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

//    public List<Course> getApprovedCoursesForUser(int userId) {
//        return enrollmentRepository.findByStatusAndUserId(userId, "APPROVED");
////                .stream()
////                .map(Enrollment::getCourse)
////                .collect(Collectors.toList());
//    }
}
