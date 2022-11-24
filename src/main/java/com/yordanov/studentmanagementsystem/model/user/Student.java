package com.yordanov.studentmanagementsystem.model.user;

import com.yordanov.studentmanagementsystem.enums.Gender;
import com.yordanov.studentmanagementsystem.model.relation.StudentProject;
import com.yordanov.studentmanagementsystem.model.schoolStuff.Subject;
import lombok.*;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Student extends User{
    @Column(name = "current_semester", length = 20)
    private String currentSemester;

    @OneToMany(mappedBy = "user")
    private Set<StudentProject> studentProjects = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_subjects",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    Set<Subject> subjects = new HashSet<>();

    public Student(){};

    public void addSubject(Subject subject){
        this.subjects.add(subject);
    }

    public void removeSubject(Subject subject){
        this.subjects.remove(subject);
    }

    public void addUserProjects(StudentProject studentProject) {
        this.studentProjects.add(studentProject);
    }

    public void removeUserProjects(StudentProject studentProject) {
        this.studentProjects.remove(studentProject);
    }
}
