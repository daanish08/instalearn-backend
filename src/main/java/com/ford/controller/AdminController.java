package com.ford.controller;
import com.ford.entity.*;
import com.ford.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


//[DONE]
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminServiceImpl adminService;

    @Autowired
    CourseServiceImpl courseService;

    @Autowired
    AttachmentServiceImpl attachmentService;

    @Autowired
    EnrollmentServiceImpl enrollmentService;

    @Autowired
    UserServiceImpl userService;

    //Creation of Admin Data
    @PostMapping("/addAdmin")
    public ResponseEntity<String> addAdmin(@RequestBody Admin admin){
        return adminService.addAdmin(admin);
    }

    @DeleteMapping("/deleteAdmin/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable int id){
        return adminService.deleteAdmin(id);
    }

    @GetMapping("/AdminList")
    public ResponseEntity<List<Admin>> getAdminList(){
        return adminService.getAllAdmin();
    }

    //creation of courses by Admin
    @PostMapping("{adminId}/addCourse")
    public ResponseEntity<String> addNewCourse(@RequestBody Course course,@PathVariable int adminId){
        System.out.println(course);
        return adminService.createCourse(adminId,course);
    }

    @PutMapping("{adminId}/{courseId}/update")
    public ResponseEntity<String> updateCourse(@RequestBody Course course,@PathVariable int adminId,@PathVariable int courseId){
        System.out.println(course+" "+adminId+" "+courseId);
        return adminService.updateCourse(adminId,courseId,course);
    }

    @DeleteMapping("{adminId}/{courseId}/delete")
    public ResponseEntity<String> deleteCourse(@PathVariable int adminId,@PathVariable int courseId){
        System.out.println(adminId+" "+courseId);
        return adminService.deleteCourse(adminId,courseId);
    }

    @GetMapping("/{adminId}/courses")
    public ResponseEntity<List<Course>> getCourses(@PathVariable int adminId) {
        return courseService.findCoursesByAdmin(adminId);
    }

    @PostMapping("/{adminId}/{courseId}/attachments/add")
    public ResponseEntity<String> addAttachments(@PathVariable int adminId, @PathVariable int courseId, @RequestBody Attachments attachments) {
        return attachmentService.createAttachments(adminId,courseId,attachments);
    }

    @PutMapping("/{adminId}/{courseId}/{attachmentId}/attachments/edit")
    public ResponseEntity<String> updateAttachments(@PathVariable int adminId, @PathVariable int courseId,@PathVariable int attachmentId, @RequestBody Attachments attachments) {
        return attachmentService.updateAttachments(adminId,courseId,attachmentId,attachments);
    }

    //Get list of user Datas
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return userService.getAllUsers();
    }


    //Enrollments status in admin---------------------------------

//    @GetMapping("A{adminId}/approvals")
////    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<List<Enrollment>> getAllPendingEnrollments(@PathVariable int adminId) {
//        try {
//            List<Enrollment> pendingEnrollments = (List<Enrollment>) enrollmentService.getPendingEnrollments(adminId);
//            System.out.println(pendingEnrollments);
//            return ResponseEntity.ok(pendingEnrollments);
//        } catch (Exception e) {
//            e.printStackTrace(); //Replace with proper logging
//            return ResponseEntity.internalServerError().build();
//        }
//    }

    @PutMapping("A{adminId}/approvals/{enrollmentId}/approve")
    public ResponseEntity<String> approveEnrollment(@PathVariable int enrollmentId,@PathVariable int adminId) {
        try {
            enrollmentService.approveEnrollment(enrollmentId,adminId);
            return ResponseEntity.ok("Enrollment approved successfully");
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        } catch (Exception e) {
            e.printStackTrace(); //Replace with proper logging
            return ResponseEntity.internalServerError().build();
        }
    }


    @PutMapping("A{adminId}/approvals/{enrollmentId}/reject")
    public ResponseEntity<String> rejectEnrollment(@PathVariable int enrollmentId,@PathVariable int adminId) {
        try {
            enrollmentService.rejectEnrollment(enrollmentId,adminId);
            return ResponseEntity.ok("Enrollment rejected successfully");
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        } catch (Exception e) {
            e.printStackTrace(); //Replace with proper logging
            return ResponseEntity.internalServerError().build();
        }
    }

}
