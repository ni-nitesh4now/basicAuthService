package com.baseauth.springjwt.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
@Slf4j
public class TestController {

    @GetMapping("/all")
    public String allAccess() {
        log.info("Public content accessed.");
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'ADMIN')")
    public String userAccess() {
        log.info("User content accessed.");
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        log.info("Moderator board accessed.");
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        log.info("Admin board accessed.");
        return "Admin Board.";
    }
}
