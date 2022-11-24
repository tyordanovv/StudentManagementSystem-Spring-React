package com.yordanov.studentmanagementsystem.model.schoolStuff;

import com.sun.istack.NotNull;
import com.yordanov.studentmanagementsystem.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(
        name = "subject",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "name")
        }
)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    @Column(name = "subject_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(length = 50)
    private String name;

    @NotNull
    private String description;

    @ManyToMany
    @JoinTable(
            name = "subject_teachers",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> teachers = new HashSet<>();

    private int semester;

    private boolean taken;
    public Subject(
            String name,
            String description,
            Set<User> teachers
    ){
        this.name = name;
        this.description = description;
        this.teachers = teachers;
        this.taken = false;
    }
}
