package com.yordanov.studentmanagementsystem.service;

import com.yordanov.studentmanagementsystem.enums.RoleType;
import com.yordanov.studentmanagementsystem.model.role.Role;
import com.yordanov.studentmanagementsystem.model.schoolStuff.Subject;
import com.yordanov.studentmanagementsystem.model.user.Student;
import com.yordanov.studentmanagementsystem.model.user.User;
import com.yordanov.studentmanagementsystem.repository.SubjectRepository;
import com.yordanov.studentmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    UserService(
            UserService userService,
            UserRepository userRepository,
            SchoolStuffService schoolStuffService
    ){
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }


    public List<User> findAllByRoles(Role roles) {
        return userRepository.findAllByRoles(roles);
    }
}
