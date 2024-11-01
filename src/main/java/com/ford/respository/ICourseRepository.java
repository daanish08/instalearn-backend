package com.ford.respository;

import com.ford.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByAdmin_AdminId(long adminId);
    Course findCourseByCourseId(int courseId);
//    long countByAdminId(int adminId);

}
