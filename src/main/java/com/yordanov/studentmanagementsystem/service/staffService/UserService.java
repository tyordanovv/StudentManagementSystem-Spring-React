package com.yordanov.studentmanagementsystem.service.staffService;

import com.yordanov.studentmanagementsystem.model.user.User;
import com.yordanov.studentmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository staffRepository;


//    public Staff getStaff() {
//        return staffRepository.getById("admin");
//    }

    public void saveUser(User user) {
        staffRepository.save(user);
    }
}
