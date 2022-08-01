package com.yordanov.studentmanagementsystem.model.schoolStuff;

import com.sun.istack.NotNull;
import com.yordanov.studentmanagementsystem.enums.StudyType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(
        name = "path",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        }
)
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Path {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "path_id")
    private Long id;

    @NotNull
    @Column(length = 50)
    private String name;

    @NotNull
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private StudyType type;

    @NotNull
    private int semesters;

    @ManyToMany
    @JoinTable(
            name = "path_subjects",
            joinColumns = @JoinColumn(name = "path_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects = new HashSet<>();
}
