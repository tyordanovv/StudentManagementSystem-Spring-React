package com.yordanov.studentmanagementsystem.web.controller;

import com.yordanov.studentmanagementsystem.enums.RoleType;
import com.yordanov.studentmanagementsystem.model.role.Role;
import com.yordanov.studentmanagementsystem.model.schoolStuff.Subject;
import com.yordanov.studentmanagementsystem.model.user.Student;
import com.yordanov.studentmanagementsystem.model.user.User;
import com.yordanov.studentmanagementsystem.repository.RoleRepository;
import com.yordanov.studentmanagementsystem.repository.UserRepository;
import com.yordanov.studentmanagementsystem.service.SchoolStuffService;
import com.yordanov.studentmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final SchoolStuffService schoolStuffService;
    private final UserRepository userRepository;

    UserController(
            UserService userService,
            RoleRepository roleRepository,
            SchoolStuffService schoolStuffService,
            UserRepository userRepository
    ){
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.schoolStuffService = schoolStuffService;
        this.userRepository = userRepository;
    }

    @GetMapping("/teachers")
    public ResponseEntity<?> getTeachers(){
        System.out.println("in teachers");

        Role teacherRole = roleRepository.findByName(RoleType.ROLE_TEACHER)
                .orElseThrow(() -> new RuntimeException("ROLE TEACHER IS NOT FOUND"));
        List<Role> roles = new ArrayList<>();
        roles.add(teacherRole);

        List <User> teachers = userService.findAllByRoles(teacherRole);
        System.out.println(teachers);
        return ResponseEntity.ok(
                teachers
        );

    }

    @PostMapping("/set-user-subject")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> setUserSubject(
            @RequestParam(name = "subjectId") List<Long> subjectIds,
            @RequestParam(name = "userId") Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Something went wrong! User was not found"));
        Subject subject;
        if (user instanceof Student) {
            for (Long subjectId : subjectIds) {
                ((Student) user).addSubject(schoolStuffService.getSubject(subjectId));
            }
            userRepository.save(user);
        } else {
            for (Long subjectId : subjectIds) {
                subject = schoolStuffService.getSubject(subjectId);
                schoolStuffService.save(subject);
            }
        }
        return ResponseEntity.ok(new RuntimeException("subjects are set"));
    }
}
