package com.yordanov.studentmanagementsystem.model.role;

import com.yordanov.studentmanagementsystem.enums.RoleType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleType name;

    public Role(){}

    public Role(RoleType name){
        this.name = name;
    }
}
