package com.yordanov.studentmanagementsystem.web.controller;

import com.yordanov.studentmanagementsystem.service.staffService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserService staffService;
//
//    @GetMapping("/users")
//    public ResponseEntity<Staff>getStaff(){
//        return ResponseEntity.ok().body(staffService.getStaff());
//    }
}
