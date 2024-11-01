package com.ford.respository;

//import com.ford.entity.Course;
import com.ford.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEnrollmentRepository extends JpaRepository<Enrollment,Integer> {
    List<Enrollment> findByStatus(String status);

//    List<Course> findByStatusAndUserId(int userId,String Status);





}
