package com.ford.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EnrollmentID")
    private Integer enrollmentId;

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "CourseID", referencedColumnName = "CourseID")
    private Course course;

    @Column(name = "EnrollmentDate")
    private Date enrollmentDate;

    @Enumerated(EnumType.STRING) // Important for better database compatibility
    @Column(name = "Status")
    private EnrollmentStatus status = EnrollmentStatus.PENDING; // Default to pending
}

enum EnrollmentStatus {
    PENDING,
    APPROVED,
    REJECTED
}
