package com.ford.service;

import com.ford.entity.Attachments;
import org.springframework.http.ResponseEntity;

public interface IAttachmentService {
    ResponseEntity<String> createAttachments(long adminId, int courseId, Attachments attachments);
//    ResponseEntity<String> deleteAttachments(long adminId, int courseId);
//    ResponseEntity<String> getAttachments(long adminId, int courseId);
    ResponseEntity<String> updateAttachments(long adminId,int courseId,int attachmentId,Attachments attachments);
}
