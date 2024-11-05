package com.ford.dto;
import lombok.AllArgsConstructor;
import lombok.Data;



@Data
public class EnrollmentDTO {
    private Integer enrollmentId;
    private String courseName;
    private String status;
    //private String action;

    public EnrollmentDTO(Integer enrollmentId, String courseName, String status) {
    }
//    private String certificate;
}