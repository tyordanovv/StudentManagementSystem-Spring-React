package com.yordanov.studentmanagementsystem.web.controller;

import com.yordanov.studentmanagementsystem.enums.StudyType;
import com.yordanov.studentmanagementsystem.model.schoolStuff.Path;
import com.yordanov.studentmanagementsystem.model.schoolStuff.Project;
import com.yordanov.studentmanagementsystem.model.schoolStuff.Subject;
import com.yordanov.studentmanagementsystem.model.user.User;
import com.yordanov.studentmanagementsystem.model.relation.UserProject;
import com.yordanov.studentmanagementsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    StudentProjectRepository studentProjectRepository;
    @Autowired
    PathRepository pathRepository;
    @Autowired
    SubjectRepository subjectRepository;

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
        User student = userRepository.findByUsername("tisho3").orElseThrow(() -> new RuntimeException("nqma tisho 3"));
        Subject subject = subjectRepository.findByName("subject1");

        Project project = new Project(
                "java9",
                "java9 be",
                subject

        );
        projectRepository.save(project);

        UserProject userProject = new UserProject();
        userProject.setProject(project);
        userProject.setUser(student);
        userProject.setNote(2);
        studentProjectRepository.save(userProject);

        return "gotovo";
    }

    @GetMapping("/path")
    public String path() throws ParseException {
//        User student = userRepository.findByUsername("tisho2").orElseThrow(() -> new RuntimeException("nqma tisho 2"));;

        Path path = new Path(
                "path1",
                "path description",
                StudyType.BACHELOR
        );
        System.out.println(1);
        pathRepository.save(path);

        Subject subject = new Subject(
                "subject1",
                "subject description",
                1
        );
        Set<User> teahers = new HashSet<>();
        User teacher = userRepository.findByUsername("tisho").orElseThrow(() -> new RuntimeException("nqma tisho"));
        teahers.add(teacher);
        subject.setTeachers(teahers);
        subjectRepository.save(subject);

        Set<Subject> subjects = new HashSet<>();
        subjects.add(subject);
        path.setSubjects(subjects);
        pathRepository.save(path);





        return "gotovo";
    }
}
