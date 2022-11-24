package com.yordanov.studentmanagementsystem.web.controller;

import com.yordanov.studentmanagementsystem.enums.Gender;
import com.yordanov.studentmanagementsystem.enums.RoleType;
import com.yordanov.studentmanagementsystem.model.role.Role;
import com.yordanov.studentmanagementsystem.model.user.Student;
import com.yordanov.studentmanagementsystem.model.user.User;
import com.yordanov.studentmanagementsystem.repository.RoleRepository;
import com.yordanov.studentmanagementsystem.repository.UserRepository;
import com.yordanov.studentmanagementsystem.requestBodies.LoginRequest;
import com.yordanov.studentmanagementsystem.requestBodies.RegisterRequest;
import com.yordanov.studentmanagementsystem.service.userDetails.UserDetailsImplementation;
import com.yordanov.studentmanagementsystem.utils.Generator;
import com.yordanov.studentmanagementsystem.web.jwt.JWTResponse;
import com.yordanov.studentmanagementsystem.web.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
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

//    @Autowired
//    UserDetailsServiceImplementation userDetailsServiceImplementation;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImplementation userDetails = (UserDetailsImplementation) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new JWTResponse(
                    jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getFirstName(),
                    userDetails.getLastName(),
                    userDetails.getBirthday().substring(0, 10),
                    userDetails.getEmail(),
                    roles));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestBody RegisterRequest registerRequest
    ) throws ParseException {

        String username = Generator.generateUsername(
                registerRequest.getFirstName(),
                registerRequest.getLastName());

        if (staffRepository.existsByUsername(username)){
            return ResponseEntity
                    .badRequest()
                    .body(new RuntimeException("Username is already taken!"));
        }

        if (staffRepository.existsByEmail(registerRequest.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new RuntimeException("Email is taken!"));
        }

        Gender userGender;
        if (registerRequest.getGender().equals("male")){
            userGender = Gender.MALE;
        }else userGender = Gender.FEMALE;

        Set<String> stringSet = registerRequest.getStrRoles();
        Set<Role> roles = new HashSet<>();

        User user = null;

        if (stringSet == null){
            Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("ROLE USER IS NOT FOUND!"));
            roles.add(userRole);
        } else {
            for (String role : stringSet){
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("ROLE ADMIN IS NOT FOUND"));
                        roles.add(adminRole);
                        user = new User();
                    }
                    case "student" -> {
                        Role studentRole = roleRepository.findByName(RoleType.ROLE_STUDENT)
                                .orElseThrow(() -> new RuntimeException("ROLE STUDENT IS NOT FOUND"));
                        roles.add(studentRole);
                        user = new Student();
                    }
                    case "teacher" -> {
                        Role teacherRole = roleRepository.findByName(RoleType.ROLE_TEACHER)
                                .orElseThrow(() -> new RuntimeException("ROLE TEACHER IS NOT FOUND"));
                        roles.add(teacherRole);
                        user = new User();
                    }
                    case "assistant" -> {
                        Role assistantRole = roleRepository.findByName(RoleType.ROLE_ASSISTANT)
                                .orElseThrow(() -> new RuntimeException("ROLE ASSISTANT IS NOT FOUND"));
                        roles.add(assistantRole);
                        user = new User();
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("ROLE USER IS NOT FOUND"));
                        roles.add(userRole);
                        user = new User();
                    }
                }
            }
        }
        
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setBirthday(registerRequest.getBirthday());
        user.setAddress(registerRequest.getAddress());
        user.setNumber(Integer.parseInt(registerRequest.getNumber()));
        user.setGender(userGender);
        user.setRoles(roles);
        staffRepository.save(user);

        return ResponseEntity.ok(new RuntimeException("successfully created"));
    }
}
