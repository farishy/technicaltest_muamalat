package com.example.technicaltest_muamalat.controller;

import com.example.technicaltest_muamalat.dto.LoginRequest;
import com.example.technicaltest_muamalat.dto.RegisterRequest;
import com.example.technicaltest_muamalat.repository.UserRepository;
import com.example.technicaltest_muamalat.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    private RegisterRequest registerRequest;
    private LoginRequest loginRequest;
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public AuthController(RegisterRequest registerRequest,
                          LoginRequest loginRequest,
                          AuthService authService) {
        this.registerRequest = registerRequest;
        this.loginRequest = loginRequest;
        this.authService = authService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
}
