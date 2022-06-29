package com.yordanov.studentmanagementsystem.web.controller;

import com.yordanov.studentmanagementsystem.enums.RoleType;
import com.yordanov.studentmanagementsystem.model.role.Role;
import com.yordanov.studentmanagementsystem.model.user.User;
import com.yordanov.studentmanagementsystem.repository.RoleRepository;
import com.yordanov.studentmanagementsystem.repository.UserRepository;
import com.yordanov.studentmanagementsystem.requestBodies.LoginRequest;
import com.yordanov.studentmanagementsystem.requestBodies.RegisterRequest;
import com.yordanov.studentmanagementsystem.service.userDetails.UserDetailsImplementation;
import com.yordanov.studentmanagementsystem.web.jwt.JWTResponse;
import com.yordanov.studentmanagementsystem.web.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository staffRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImplementation userDetailsImplementation = (UserDetailsImplementation) authentication.getPrincipal();
        List<String> roles = userDetailsImplementation.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new JWTResponse(jwt,
                        userDetailsImplementation.getId(),
                        userDetailsImplementation.getUsername(),
                        userDetailsImplementation.getEmail(),
                        roles));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        if (staffRepository.existsByUsername(registerRequest.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new RuntimeException("Username is already taken!"));
        }

        if (staffRepository.existsByEmail(registerRequest.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new RuntimeException("Email is taken!"));
        }

        User user = new User(
                registerRequest.getUsername(),
                registerRequest.getPassword(),
//                passwordEncoder.encode(registerRequest.getPassword()));
                registerRequest.getEmail()
        );
        Set<String> stringSet = registerRequest.getStrRoles();
        Set<Role> roles = new HashSet<>();

        if (stringSet == null){
            Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("ROLE USER IS NOT FOUND!"));
            roles.add(userRole);
        } else {
            stringSet.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("ROLE ADMIN IS NOT FOUND"));
                        roles.add(adminRole);
                        break;
                    case "student":
                        Role studentRole = roleRepository.findByName(RoleType.ROLE_STUDENT)
                                .orElseThrow(() -> new RuntimeException("ROLE STUDENT IS NOT FOUND"));
                        roles.add(studentRole);
                        break;
                    case "teacher":
                        Role teacherRole = roleRepository.findByName(RoleType.ROLE_TEACHER)
                                .orElseThrow(() -> new RuntimeException("ROLE TEACHER IS NOT FOUND"));
                        roles.add(teacherRole);
                        break;
                    case "assistant":
                        Role assistantRole = roleRepository.findByName(RoleType.ROLE_ASSISTANT)
                                .orElseThrow(() -> new RuntimeException("ROLE ASSISTANT IS NOT FOUND"));
                        roles.add(assistantRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("ROLE USER IS NOT FOUND"));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        staffRepository.save(user);

        return ResponseEntity.ok(new RuntimeException("successfully created"));
    }
}
