package com.yordanov.studentmanagementsystem.web.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JWTResponse {
    private String accessToken;
    private String type = "Bearer";
    private Long id;
    private String username;

    private String firstName;

    private String lastName;

    private String birthday;
    private String email;
    private List<String> roles;

    public JWTResponse(
            String accessToken,
            Long id,
            String username,
            String firstName,
            String lastName,
            String birthday,
            String email,
            List<String> roles
    ){
        this.accessToken = accessToken;
        this.id=id;
        this.username=username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email=email;
        this.roles=roles;
    }
}
