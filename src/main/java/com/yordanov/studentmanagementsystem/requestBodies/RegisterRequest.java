package com.yordanov.studentmanagementsystem.requestBodies;

import com.sun.jdi.PrimitiveValue;
import com.yordanov.studentmanagementsystem.enums.Gender;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String birthday;
    private String email;
    private String password;
    Set<String> strRoles;
    private String address;
    private String gender;
    private String number;


}
