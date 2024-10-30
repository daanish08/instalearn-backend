package com.ford.service;

import com.ford.entity.Admin;
import com.ford.entity.Enrollment;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IEnrollmentService {
    public ResponseEntity<String> addEnrollment(int courseId,int userId);
    public ResponseEntity<List<Enrollment>> getPendingEnrollments(int adminId);

    public  ResponseEntity<String> approveEnrollment(int enrollmentId,int adminId);
    public  ResponseEntity<String> rejectEnrollment(int enrollmentId,int adminId);
}
