package com.yordanov.studentmanagementsystem.service;

import com.yordanov.studentmanagementsystem.enums.StudyType;
import com.yordanov.studentmanagementsystem.model.schoolStuff.Path;
import com.yordanov.studentmanagementsystem.model.schoolStuff.Subject;
import com.yordanov.studentmanagementsystem.model.user.User;
import com.yordanov.studentmanagementsystem.repository.PathRepository;
import com.yordanov.studentmanagementsystem.repository.SubjectRepository;
import com.yordanov.studentmanagementsystem.repository.UserRepository;
import com.yordanov.studentmanagementsystem.requestBodies.CreatePath;
import com.yordanov.studentmanagementsystem.requestBodies.CreateSubject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SchoolStuffService {

    SubjectRepository subjectRepository;
    PathRepository pathRepository;
    UserRepository userRepository;

    SchoolStuffService(
            SubjectRepository subjectRepository,
            PathRepository pathRepository,
            UserRepository userRepository
    ){
        this.subjectRepository = subjectRepository;
        this.pathRepository = pathRepository;
        this.userRepository = userRepository;
    }

    public void createSubject(CreateSubject subjectRequest, Set<User> teachers) {
        Subject subject = new Subject(
                subjectRequest.getName(),
                subjectRequest.getDescription(),
                teachers
        );
        subjectRepository.save(subject);
    }

    public List<Subject> findAllFreeSubjects() {
        return subjectRepository.findAllByTakenIsFalse();
    }

    public Subject updateAndReturnById(Long id, String key){
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject Not Found"));
        subject.setSemester(Integer.parseInt(key));
        subject.setTaken(true);
        subjectRepository.save(subject);
        return subject;
    }

    public void createPath(CreatePath request, Set<Subject> subjects) {
        Path path = new Path();
        path.setName(request.getName());
        path.setDescription(request.getDescription());

        if (request.getType().equals("bachelor")) path.setType(StudyType.BACHELOR);
        else if (request.getType().equals("master")) path.setType(StudyType.MASTER);

        path.setSubjects(subjects);
        pathRepository.save(path);
    }

    public List<Subject> getAllSubjectsbyUsername(String currentUserName) {
        return subjectRepository.findAllByUsername(currentUserName);
    }

    public Subject getSubject(Long subjectId) {
        return subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Something went wrong! Subject was not found!"));
    }

    public void save(Subject subject) {
        subjectRepository.save(subject);
    }
}
