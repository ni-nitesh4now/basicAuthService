package com.baseauth.springjwt.controllers;

import com.baseauth.springjwt.payload.request.LoginRequest;
import com.baseauth.springjwt.payload.request.UserProfileRequest;
import com.baseauth.springjwt.service.CredentialsService;
import com.baseauth.springjwt.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private CredentialsService credentialsService;
    @Autowired
    private UserProfileService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(credentialsService.authenticateUser(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserProfileRequest signUpRequest) {
        return ResponseEntity.ok(userService.createUserProfile(signUpRequest));
    }
}
