package com.yordanov.studentmanagementsystem.requestBodies;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
//@NoArgsConstructor
public class LoginRequest {
    private String username;
    private String password;
}
