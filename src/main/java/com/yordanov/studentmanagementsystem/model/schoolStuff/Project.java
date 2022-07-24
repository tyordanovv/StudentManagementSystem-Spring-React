package com.yordanov.studentmanagementsystem.model.schoolStuff;

import com.sun.istack.NotNull;
import com.yordanov.studentmanagementsystem.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Table(name = "projects")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project {
    @Id
    @Column(name = "project_id")
    private UUID id;

    @NotNull
    @Column(length = 50)
    private String name;

    @NotNull
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_projects",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> students;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToOne
    @JoinColumn(name = "note_id")
    private Note note;

}
