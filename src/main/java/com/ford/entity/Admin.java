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
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adminID")
    private long adminId;

    @Column(name = "name", nullable = false, unique = true,length = 255)
    private String name;

    @Column(name = "email", nullable = false, unique = true,length = 255)
    private String email;

    @Column(name = "phone", nullable = false, unique = true,length = 10)
    private String phone;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true) // Map to the admin field in Course
    private Set<Course> courses = new HashSet<>();
}
