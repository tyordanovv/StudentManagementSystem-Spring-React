package com.yordanov.studentmanagementsystem.model.relation;

import com.yordanov.studentmanagementsystem.model.schoolStuff.Project;
import com.yordanov.studentmanagementsystem.model.user.User;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "student_projects")
@NoArgsConstructor
public class UserProject {
    @Id
    @GeneratedValue
    @Column(name = "student_project_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private Project project;

    private int note;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project){
        this.project = project;
    }

    public int getNote(){
        return note;
    }

    public void setNote(int note){
        this.note = note;
    }

}
