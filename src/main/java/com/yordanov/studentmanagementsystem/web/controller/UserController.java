package com.yordanov.studentmanagementsystem.web.controller;

import com.yordanov.studentmanagementsystem.enums.RoleType;
import com.yordanov.studentmanagementsystem.model.role.Role;
import com.yordanov.studentmanagementsystem.model.user.User;
import com.yordanov.studentmanagementsystem.repository.RoleRepository;
import com.yordanov.studentmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {

    @Autowired
    UserService staffService;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/teachers")
    public ResponseEntity<?> getTeachers(){

        Role teacherRole = roleRepository.findByName(RoleType.ROLE_TEACHER)
                .orElseThrow(() -> new RuntimeException("ROLE TEACHER IS NOT FOUND"));
        List<Role> roles = new ArrayList<>();
        roles.add(teacherRole);

        List <User> teachers = staffService.findAllByRoles(teacherRole);
        return ResponseEntity.ok(
                teachers
        );

    };
}
