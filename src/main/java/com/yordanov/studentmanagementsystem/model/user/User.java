package com.yordanov.studentmanagementsystem.model.user;

import com.sun.istack.NotNull;
import com.yordanov.studentmanagementsystem.model.role.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 50, name = "username")
    private String username;

    @NotNull
    @Column(name = "birthday")
    private Date birthday;

    @NotNull
    @Column(name = "password",length = 60)
    private String password;

    //@NotNull
    @Column(name = "first_name", length = 20)
    private String firstName;

    //@NotNull
    @Column(name = "last_name", length = 20)
    private String lastName;

    @NotNull
    @Column(length = 50, name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(){}

    public User(
            String username,
            String password,
            String firstName,
            String lastName,
            String email,
            String birthday
    ) throws ParseException {
        this.password=password;
        this.username=username;
        this.email=email;
        this.firstName=firstName;
        this.lastName=lastName;
        this.birthday = new SimpleDateFormat("dd/MM/yyyy").parse(birthday);
    }

    public String getBirthday(){
        if (birthday == null){
            return "00/00/0000";
        }else return birthday.toString();
    }
}
