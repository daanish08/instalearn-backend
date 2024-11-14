package com.ford.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseDto {
    private Integer courseId;
    private String courseName;
    private String description;
    private String instructor;
    private Integer duration;
    private String courseURL;
    private String githubURL;
    private String driveURL;
    private boolean isEnrolled;
}

