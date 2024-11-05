package com.ford.service;

import com.ford.entity.Contact;
import com.ford.respository.IContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements IContactService{

    @Autowired
    private IContactRepository contactRepository;


    @Override
    public ResponseEntity<String> addFeedback(Contact contact) {
        try{
            Contact newfeedback = contactRepository.save(contact);
            return ResponseEntity.status(HttpStatus.CREATED).body(newfeedback.toString()+" ADDED SUCCESFULLY");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<Contact>> getFeedback() {
        return ResponseEntity.status(HttpStatus.CREATED).body( contactRepository.findAll());
    }

    public Long getFeedbackCount() {
        return contactRepository.count(); // count() returns a long
    }
}
