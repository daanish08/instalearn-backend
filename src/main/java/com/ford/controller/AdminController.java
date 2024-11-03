package com.ford.controller;
import com.ford.entity.*;
import com.ford.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//[DONE]
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    AdminServiceImpl adminService;

    @Autowired
    CourseServiceImpl courseService;



    @Autowired
    EnrollmentServiceImpl enrollmentService;

    @Autowired
    UserServiceImpl userService;


    //Creation of Admin Data
    //http://localhost:8080/instalearn/admin/addAdmin
    @PostMapping("/addAdmin")
    public ResponseEntity<Map<String,Object>> addAdmin(@RequestBody Admin admin){
        return adminService.addAdmin(admin);
    }

    //http://localhost:8080/instalearn/admin/deleteAdmin/1
    @DeleteMapping("/deleteAdmin/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable int id){
        return adminService.deleteAdmin(id);
    }

    //http://localhost:8080/instalearn/admin/AdminList
    @GetMapping("/AdminList")
    public ResponseEntity<List<Admin>> getAdminList(){
        return adminService.getAllAdmin();
    }

    //http://localhost:8080/instalearn/admin/AdminList/1
    @GetMapping("/AdminList/{adminId}")
    public ResponseEntity<Admin> getAdminListById(@PathVariable long adminId){
        return adminService.getAdminById(adminId);
    }

    //COURSES

    //creation of courses by Admin
    //http://localhost:8080/instalearn/admin/A2/addCourse
    @PostMapping("A{adminId}/addCourse")
    public ResponseEntity<String> addNewCourse(@RequestBody Course course,@PathVariable long adminId){
        System.out.println(course);
        adminService.createCourse(adminId,course);
        return ResponseEntity.status(HttpStatus.CREATED).body("Course added successfully");
    }

    //http://localhost:8080/instalearn/admin/A2/C1/update
    @PutMapping("A{adminId}/C{courseId}/update")
    public ResponseEntity<String> updateCourse(@RequestBody Course course,@PathVariable long adminId,@PathVariable int courseId){
        System.out.println(course+" "+adminId+" "+courseId);
        return adminService.updateCourse(adminId,courseId,course);
    }

    //http://localhost:8080/instalearn/admin/A2/C1/delete
    @DeleteMapping("A{adminId}/C{courseId}/delete")
    public ResponseEntity<String> deleteCourse(@PathVariable long adminId,@PathVariable int courseId){
        System.out.println(adminId+" "+courseId);
        return adminService.deleteCourse(adminId,courseId);
    }

    //http://localhost:8080/instalearn/admin/2/courses
    @GetMapping("/A{adminId}/courses")
    public ResponseEntity<List<Course>> getCourses(@PathVariable long adminId) {
        return courseService.findCoursesByAdmin(adminId);
    }

    //http://localhost:8080/instalearn/admin/users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return userService.getAllUsers();
    }

    //http://localhost:8080/instalearn/admin/usersCount
    @GetMapping("/usersCount")
    public ResponseEntity<Long> getUsersCount() {
        Long count = userService.getUsersCount(); // Ensure this returns a Long
        return ResponseEntity.ok(count); // Use .ok() for 200 status
    }




    //Enrollments status in admin---------------------------------

    //http://localhost:8080/instalearn/admin/A1/approvals
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("A{adminId}/approvals")
    public ResponseEntity<List<Enrollment>> getPendingEnrollments(@PathVariable long adminId) {
        List<Enrollment> enrollments = enrollmentService.findPendingEnrollmentsByAdminId(adminId);
//        System.out.println(enrollments);
        return ResponseEntity.ok(enrollments);
    }
    //http://localhost:8080/instalearn/admin/A2/approvals/E1/approve
    @PutMapping("A{adminId}/approvals/E{enrollmentId}/approve")
    public ResponseEntity<String> approveEnrollment(@PathVariable int enrollmentId,@PathVariable long adminId) {
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

    //http://localhost:8080/instalearn/admin/A1/approvals/E1/reject
    @PutMapping("A{adminId}/approvals/E{enrollmentId}/reject")
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
