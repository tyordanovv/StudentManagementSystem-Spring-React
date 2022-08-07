package com.yordanov.studentmanagementsystem.web.controller;

import com.yordanov.studentmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {

    @Autowired
    UserService staffService;

    @GetMapping("/teachers")
    public ResponseEntity<?> getTeachers(){
        return new ResponseEntity<>();

    };
}
