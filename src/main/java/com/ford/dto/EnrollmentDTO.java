package com.ford.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation for getters, setters, toString, etc.
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDTO {
    private int id;
    private String username;
    private String courseTitle;
    private String status;
}
