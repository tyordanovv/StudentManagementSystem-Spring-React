package com.yordanov.studentmanagementsystem.web.controller;

import com.yordanov.studentmanagementsystem.enums.RoleType;
import com.yordanov.studentmanagementsystem.model.role.Role;
import com.yordanov.studentmanagementsystem.model.schoolStuff.Subject;
import com.yordanov.studentmanagementsystem.model.user.User;
import com.yordanov.studentmanagementsystem.repository.RoleRepository;
import com.yordanov.studentmanagementsystem.repository.UserRepository;
import com.yordanov.studentmanagementsystem.requestBodies.CreateSubject;
import com.yordanov.studentmanagementsystem.service.SchoolStuffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/school-stuff/")
public class SchoolStuffController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SchoolStuffService schoolStuffService;

    @PostMapping("save/subject")
    public ResponseEntity<?> saveSubject(
            @RequestBody CreateSubject subject
            ){

        Set<User> teachers = new HashSet<>();
        System.out.println(subject.getTeachers());
        for (int teacherId : subject.getTeachers()){
            User teacher = userRepository.findById(Long.valueOf(teacherId))
                    .orElseThrow(() -> new RuntimeException("Teacher was not found!"));
            teachers.add(teacher);
        }

        schoolStuffService.createSubject(subject, teachers);


        return ResponseEntity.ok(new RuntimeException("successfully created"));

    }
}
