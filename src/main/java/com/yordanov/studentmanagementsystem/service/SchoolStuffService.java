package com.yordanov.studentmanagementsystem.service;

import com.yordanov.studentmanagementsystem.model.schoolStuff.Subject;
import com.yordanov.studentmanagementsystem.model.user.User;
import com.yordanov.studentmanagementsystem.repository.SubjectRepository;
import com.yordanov.studentmanagementsystem.requestBodies.CreateSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SchoolStuffService {

    @Autowired
    SubjectRepository subjectRepository;

    public void createSubject(CreateSubject subjectRequest, Set<User> teachers) {
        Subject subject = new Subject(
                subjectRequest.getName(),
                subjectRequest.getDescription(),
                teachers
        );
        subjectRepository.save(subject);
    }
}
