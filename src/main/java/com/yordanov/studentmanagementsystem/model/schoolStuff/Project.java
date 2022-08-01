package com.yordanov.studentmanagementsystem.model.schoolStuff;

import com.sun.istack.NotNull;
import com.yordanov.studentmanagementsystem.model.relation.UserProject;
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
    private Set<UserProject> userProjects = new HashSet<>();

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
    public Set<UserProject> getUserProjects() {
        return userProjects;
    }

    public void setUserProjects(Set<UserProject> projects) {
        this.userProjects = projects;
    }

    public void addUserProjects(UserProject userProject) {
        this.userProjects.add(userProject);
    }

}
