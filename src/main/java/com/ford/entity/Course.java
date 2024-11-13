package com.ford.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseID")
    private Integer courseId;

    @Column(name = "courseName", nullable = false, unique = true, length = 255)
    private String CourseName;

    @Column(name = "description")
    private String description;

    @Column(name = "instructor", length = 255)
    private String instructor;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "courseUrl")
    private String courseURL;

    @Column(name = "githubUrl")
    private String githubURL;

    @Column(name = "driveUrl")
    private String driveURL;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "adminID", referencedColumnName = "adminID")
    private Admin admin;


    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Enrollment> enrollments;
}
