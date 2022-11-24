package com.yordanov.studentmanagementsystem.model.user;

import com.sun.istack.NotNull;
import com.yordanov.studentmanagementsystem.enums.Gender;
import com.yordanov.studentmanagementsystem.model.role.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(
        name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    @Column(length = 50, name = "username")
    private String username;

    @NotNull
    @Column(name = "birthday")
    private Date birthday;

    @NotNull
    @Column(name = "number", length = 15)
    private int number;

    @NotNull
    @Column(name = "gender")
    private Gender gender;

    @NotNull
    @Column(name = "address", length = 50)
    private String address;

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
            String birthday,
            String address,
            int number,
            Gender gender
    ) throws ParseException {
        this.password=password;
        this.username=username;
        this.email=email;
        this.firstName=firstName;
        this.lastName=lastName;
        this.birthday = new SimpleDateFormat("dd/MM/yyyy").parse(birthday);
        this.address = address;
        this.number = number;
        this.gender = gender;
    }

    public String getBirthday(){
        if (birthday == null){
            return "00/00/0000";
        }else return birthday.toString();
    }

    public void setBirthday(String birthday) throws ParseException {
        this.birthday = new SimpleDateFormat("dd/MM/yyyy").parse(birthday);
    }
}
