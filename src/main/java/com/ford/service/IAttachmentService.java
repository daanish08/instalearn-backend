package com.ford.service;

import com.ford.entity.Attachments;
import org.springframework.http.ResponseEntity;

public interface IAttachmentService {
    ResponseEntity<String> createAttachments(int adminId, int courseId, Attachments attachments);
//    ResponseEntity<String> deleteAttachments(int adminId, int courseId);
//    ResponseEntity<String> getAttachments(int adminId, int courseId);
    ResponseEntity<String> updateAttachments(int adminId,int courseId,int attachmentId,Attachments attachments);
}
