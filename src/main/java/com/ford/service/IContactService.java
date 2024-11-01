package com.ford.service;

import com.ford.entity.Admin;
import com.ford.entity.Contact;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IContactService {
    public ResponseEntity<String> addFeedback(Contact contact);
    public ResponseEntity<List<Contact>> getFeedback();
//    public ResponseEntity<List<Admin>> getFeedback();
}
