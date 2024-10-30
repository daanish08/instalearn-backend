package com.ford.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourseID")
    private Integer courseId;

    @Column(name = "CourseName", nullable = false, unique = true, length = 255)
    private String CourseName;

    @Column(name = "Description")
    private String description;

    @Column(name = "Instructor", length = 255)
    private String instructor;

    @Column(name = "Duration")
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "AdminID", referencedColumnName = "AdminID")
    private Admin admin;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Enrollment> enrollments;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Attachments> attachments;
}
