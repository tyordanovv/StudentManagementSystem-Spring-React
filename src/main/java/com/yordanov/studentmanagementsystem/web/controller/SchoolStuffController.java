package com.yordanov.studentmanagementsystem.web.controller;

import com.yordanov.studentmanagementsystem.model.schoolStuff.Subject;
import com.yordanov.studentmanagementsystem.model.user.User;
import com.yordanov.studentmanagementsystem.repository.UserRepository;
import com.yordanov.studentmanagementsystem.requestBodies.CreatePath;
import com.yordanov.studentmanagementsystem.requestBodies.CreateSubject;
import com.yordanov.studentmanagementsystem.service.SchoolStuffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/school-stuff/")
public class SchoolStuffController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SchoolStuffService schoolStuffService;

    @PostMapping("save/subject")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveSubject(@RequestBody CreateSubject subject){

        Set<User> teachers = new HashSet<>();
        for (int teacherId : subject.getTeachers()){
            User teacher = userRepository.findById(Long.valueOf(teacherId))
                    .orElseThrow(() -> new RuntimeException("Teacher was not found!"));
            teachers.add(teacher);
        }

        schoolStuffService.createSubject(subject, teachers);

        return ResponseEntity.ok(new RuntimeException("successfully created"));

    }

    @PostMapping("save/path")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> savePath(@RequestBody CreatePath path) {
        Set<Subject> subjects = new HashSet<>();
        for (Map.Entry<String, int[]> entry : path.getSemesterSubjects().entrySet()) {
            for (int subjectId : entry.getValue()) {
                Subject subject = schoolStuffService
                        .updateAndReturnById((long) subjectId, entry.getKey());
                subjects.add(subject);
            }
        }
        schoolStuffService.createPath(path, subjects);
        return ResponseEntity.ok(new RuntimeException("suc"));
    }

    @GetMapping("get-subjects")
    public ResponseEntity<?> getTeachers(){
        List <Subject> subjects = schoolStuffService.findAllFreeSubjects();
        return ResponseEntity.ok(
                subjects
        );

    }

    @GetMapping("get-user-subjects")
    public ResponseEntity<?> getUserSubjects(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return ResponseEntity.ok(schoolStuffService.getAllSubjectsbyUsername(currentUserName));
        }else {
            return ResponseEntity.ok(new RuntimeException("No User"));
        }
    }

}
