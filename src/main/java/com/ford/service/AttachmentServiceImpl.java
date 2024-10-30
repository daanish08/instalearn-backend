package com.ford.service;

import com.ford.entity.Attachments;
import com.ford.entity.Course;
import com.ford.respository.IAttachmentRepository;
import com.ford.respository.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AttachmentServiceImpl implements IAttachmentService {

    private final IAttachmentRepository attachmentsRepository;
    private final ICourseRepository courseRepository;

    @Autowired
    public AttachmentServiceImpl(IAttachmentRepository attachmentsRepository, ICourseRepository courseRepository) {
        this.attachmentsRepository = attachmentsRepository;
        this.courseRepository = courseRepository;
    }

    public ResponseEntity<String> createAttachments(int adminId, int courseId, Attachments attachments) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if (!courseOptional.isPresent()) {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }

        Course course = courseOptional.get();

        // Set the course for the attachment
        attachments.setCourse(course);
        attachments.setUploadedAt(LocalDateTime.now());

        // Save the attachment
        attachmentsRepository.save(attachments);

        return new ResponseEntity<>("Attachment created successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateAttachments(int adminId, int courseId, int attachmentId, Attachments attachments) {
        // Verify the course exists
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (!courseOptional.isPresent()) {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }

        // Verify the attachment exists
        Optional<Attachments> attachmentOptional = attachmentsRepository.findById((long) attachmentId);
        if (!attachmentOptional.isPresent()) {
            return new ResponseEntity<>("Attachment not found", HttpStatus.NOT_FOUND);
        }

        Attachments existingAttachment = attachmentOptional.get();

        // Check if the existing attachment is associated with the given course
        if (!existingAttachment.getCourse().getCourseId().equals(courseId)) {
            return new ResponseEntity<>("Attachment does not belong to the specified course", HttpStatus.BAD_REQUEST);
        }

        // Update fields
        existingAttachment.setFileName(attachments.getFileName());
        existingAttachment.setFileType(attachments.getFileType());
        existingAttachment.setFilePath(attachments.getFilePath());

        // If you want to update the timestamp to the current time, use this:
        existingAttachment.setUploadedAt(LocalDateTime.now());
        // Otherwise, keep it unchanged if it should remain the original upload time

        // Save the updated attachment
        attachmentsRepository.save(existingAttachment);

        return new ResponseEntity<>("Attachment updated successfully", HttpStatus.OK);
    }
}

