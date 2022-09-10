package com.yordanov.studentmanagementsystem.model.schoolStuff;

import com.sun.istack.NotNull;
import com.yordanov.studentmanagementsystem.model.relation.StudentProject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Table(
        name = "project",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project {
    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(length = 50)
    private String name;

    @NotNull
    private String description;

    @OneToMany(mappedBy = "project")
    private Set<StudentProject> studentProjects = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Project(
            String name,
            String description,
            Subject subject
    ){
        this.name = name;
        this.description = description;
        this.subject = subject;
    }
    public Set<StudentProject> getStudentProjects() {
        return studentProjects;
    }

    public void setStudentProjects(Set<StudentProject> projects) {
        this.studentProjects = projects;
    }

    public void addUserProjects(StudentProject studentProject) {
        this.studentProjects.add(studentProject);
    }

}
