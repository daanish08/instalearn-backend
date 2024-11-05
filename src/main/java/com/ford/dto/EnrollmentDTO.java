package com.ford.dto;
import com.ford.entity.EnrollmentStatus;
import lombok.Data;



@Data
public class EnrollmentDTO {
    private Integer enrollmentId;
    private String courseName;
    private EnrollmentStatus status;
    //private String action;

    public EnrollmentDTO(Integer enrollmentId, String courseName, String status, EnrollmentStatus enrollmentStatus) {
    }
//    private String certificate;
}