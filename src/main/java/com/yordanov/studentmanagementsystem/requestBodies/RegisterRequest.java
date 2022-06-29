package com.yordanov.studentmanagementsystem.requestBodies;

import com.sun.jdi.PrimitiveValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    Set<String> strRoles;


}
