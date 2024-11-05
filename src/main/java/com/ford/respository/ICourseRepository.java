package com.ford.respository;

import com.ford.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByAdmin_AdminId(long adminId);
//    Long countByAdminId(long adminId);

    @Query("SELECT COUNT(c) FROM Course c WHERE c.admin.adminId = :adminId")
    long countCoursesByAdminId(@Param("adminId") long adminId);

    Course findCourseByCourseId(int courseId);
//    long countByAdminId(int adminId);

}
