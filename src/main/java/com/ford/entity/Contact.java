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
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contactID")
    private Integer contactId;

    @Column(name = "Name", nullable = false, unique = true, length = 255)
    private String Name;

    @Column(name = "contact", nullable = false,  length = 15)
    private String contact;

    @Column(name = "comment",nullable=false)
    private String comment;

    @Column(name = "enquiryType")
    private String enquiryType;

}
