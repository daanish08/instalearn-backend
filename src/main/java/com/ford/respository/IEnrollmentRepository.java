package com.ford.respository;

import com.ford.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEnrollmentRepository extends JpaRepository<Enrollment,Integer> {
    List<Enrollment> findByStatus(String status);

    @Query("SELECT e FROM Enrollment e JOIN FETCH e.course c JOIN FETCH c.admin a WHERE e.status = 'PENDING' AND a.adminId = :adminId")
    List<Enrollment> findPendingEnrollmentsByAdminId(@Param("adminId") int adminId);


}
