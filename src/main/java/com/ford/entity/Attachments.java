package com.ford.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attachments")
public class Attachments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AttachmentID")
    private Long attachmentID;

    @ManyToOne
    @JoinColumn(name = "CourseID", nullable = false)
    private Course course;

    @Column(name = "FileName",unique = true, nullable = false)
    private String fileName;

    @Column(name = "FileType", nullable = false)
    private String fileType;

    @Column(name = "FilePath", nullable = false)
    private String filePath;

    @Column(name = "UploadedAt", nullable = false)
    private LocalDateTime uploadedAt;
}
