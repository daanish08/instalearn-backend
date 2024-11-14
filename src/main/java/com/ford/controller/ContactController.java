package com.ford.controller;

import com.ford.entity.Admin;
import com.ford.entity.Contact;
import com.ford.service.AdminServiceImpl;
import com.ford.service.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//[DONE]

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/admin/feedbacks")
public class ContactController {

    @Autowired
    ContactServiceImpl contactService;

    //Creation of Admin Data
    //http://localhost:8080/instalearn/admin/feedbacks/add
    @PostMapping("/add")
    public ResponseEntity<String> addAdmin(@RequestBody Contact contact){
        return contactService.addFeedback(contact);
    }

    //http://localhost:8080/instalearn/admin/feedbacks/list
    @GetMapping("/list")
    public ResponseEntity<List<Contact>> getFeedbackList(){
        return contactService.getFeedback();
    }


    //http://localhost:8080/instalearn/admin/feedbacks/count
    @GetMapping("/count")
    public ResponseEntity<Long> getFeedbackCount(){
        Long count = contactService.getFeedbackCount(); // Ensure this returns a Long
        return ResponseEntity.ok(count);
    }

}
