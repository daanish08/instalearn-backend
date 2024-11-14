package com.ford.service;

import com.ford.dto.CourseDto;
import com.ford.respository.ICourseRepository;
import com.ford.respository.IEnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ford.entity.Course;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private final ICourseRepository courseRepository;
    @Autowired
    private IEnrollmentRepository enrollmentRepository;

    @Autowired
    public CourseServiceImpl(ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public ResponseEntity<List<Course>> findCoursesByAdmin(long adminId) {
        List<Course> courses = courseRepository.findAllByAdminIdOrNull(adminId);
        System.out.println(courses);
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

    public Long getCourseCount(long adminId) {
        return courseRepository.countCoursesByAdminId(adminId); // count() returns a long
    }

    public int getTotalCourseCount(){
        return (int) courseRepository.count();
    }

    public List<CourseDto> getCoursesWithEnrollmentStatus(int userId) {
        // Fetch all enrollments for the user
        Set<Integer> enrolledCourseIds = enrollmentRepository.findEnrollmentsByUserId(userId)
                .stream()
                .map(enrollment -> enrollment.getCourse().getCourseId())
                .collect(Collectors.toSet());

        // Fetch all courses and map to DTOs
        return courseRepository.findAll().stream().map(course -> {
            boolean isEnrolled = enrolledCourseIds.contains(course.getCourseId());
            return new CourseDto(
                    course.getCourseId(),
                    course.getCourseName(),
                    course.getDescription(),
                    course.getInstructor(),
                    course.getDuration(),
                    course.getCourseURL(),
                    course.getGithubURL(),
                    course.getDriveURL(),
                    isEnrolled
            );
        }).collect(Collectors.toList());
    }

//    public List<Course> getApprovedCoursesForUser(int userId) {
//        return enrollmentRepository.findByStatusAndUserId(userId, "APPROVED");
////                .stream()
////                .map(Enrollment::getCourse)
////                .collect(Collectors.toList());
//    }
}
