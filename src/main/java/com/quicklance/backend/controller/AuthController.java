package com.quicklance.backend.controller;

import com.quicklance.backend.model.User;
import com.quicklance.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User existing = userRepo.findByEmail(user.getEmail());
        if (existing != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("EMAIL_EXISTS");
        }

        userRepo.save(user);
        return ResponseEntity.ok("SUCCESS");
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginData) {

        User user = userRepo.findByEmail(loginData.getEmail());

        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("USER_NOT_FOUND");
        }

        if (!user.getPassword().equals(loginData.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("INVALID_PASSWORD");
        }

        return ResponseEntity.ok(user);
    }
}
