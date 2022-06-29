package com.yordanov.studentmanagementsystem.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess(){
        return "public content";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('TEACHER') or hasRole('STUDENT') or hasRole('ADMIN') or hasRole('ASSISTANT')")
    public String userAccess(){
        return "user access";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ASSISTANT')")
    public String adminAccess(){
        return "admin access";
    }
}
