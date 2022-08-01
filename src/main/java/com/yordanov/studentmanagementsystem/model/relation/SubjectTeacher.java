package com.yordanov.studentmanagementsystem.model.relation;

import com.yordanov.studentmanagementsystem.model.schoolStuff.Project;
import com.yordanov.studentmanagementsystem.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//@Table(
//        name = "subject_teachers"
//)
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//public class SubjectTeacher {
//    @Id
//    @GeneratedValue
//    @Column(name = "student_project_id")
//    private Long id;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "student_id")
//    private User user;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "project_id")
//    private Project project;
//}
