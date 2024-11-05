package com.ford.respository;

//import com.ford.entity.Course;
import com.ford.dto.EnrollmentDTO;
import com.ford.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEnrollmentRepository extends JpaRepository<Enrollment,Integer> {
    List<Enrollment> findByStatus(String status);

    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.user.userId = :userId")
    long countEnrollmentsByUserId(@Param("userId") Integer userId);


//    @Query("SELECT new com.ford.dto.EnrollmentDTO(e.enrollmentId, u.userName, c.CourseName , e.status) " +
//            "FROM Enrollment e " +
//            "JOIN e.user u " +
//            "JOIN e.course c " +
//            "WHERE u.userId = :userId")
//    List<EnrollmentDTO> findEnrollmentDetailsByUserId(@Param("userId") int userId);


    @Query("SELECT e FROM Enrollment e WHERE e.course.admin.adminId = :adminId AND e.status = 'PENDING'")
    List<Enrollment> findPendingEnrollmentsByAdminId(@Param("adminId") long adminId);
//    List<Course> findByStatusAndUserId(int userId,String Status);





}
