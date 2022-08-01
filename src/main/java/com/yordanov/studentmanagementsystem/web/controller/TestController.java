package com.yordanov.studentmanagementsystem.web.controller;

import com.yordanov.studentmanagementsystem.model.schoolStuff.Project;
import com.yordanov.studentmanagementsystem.model.user.User;
import com.yordanov.studentmanagementsystem.model.relation.UserProject;
import com.yordanov.studentmanagementsystem.repository.ProjectRepository;
import com.yordanov.studentmanagementsystem.repository.StudentProjectRepository;
import com.yordanov.studentmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    StudentProjectRepository studentProjectRepository;

    @GetMapping("/all")
    public String allAccess() {
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

    @GetMapping("/save")
    public String save() throws ParseException {
        User student = userRepository.findByUsername("tisho2").orElseThrow(() -> new RuntimeException("nqma tisho 2"));;

        Project project = new Project(
                "java4",
                "java 1be"
        );
        projectRepository.save(project);

        UserProject userProject = new UserProject();
        userProject.setProject(project);
        userProject.setUser(student);
        userProject.setNote(2);
        studentProjectRepository.save(userProject);

        return "gotovo";
    }
}
