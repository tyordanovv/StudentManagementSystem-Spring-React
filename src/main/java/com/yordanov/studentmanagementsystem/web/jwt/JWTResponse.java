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
    private String email;
    private List<String> roles;

    public JWTResponse(
            String accessToken,
            Long id,
            String username,
            String email,
            List<String> roles
    ){
        this.accessToken = accessToken;
        this.id=id;
        this.username=username;
        this.email=email;
        this.roles=roles;
    }
}
