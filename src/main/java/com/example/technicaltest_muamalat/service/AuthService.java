package com.example.technicaltest_muamalat.service;

import com.example.technicaltest_muamalat.constant.StatusConstant;
import com.example.technicaltest_muamalat.dto.*;
import com.example.technicaltest_muamalat.jwt.JwtProvider;
import com.example.technicaltest_muamalat.model.DefaultResponse;
import com.example.technicaltest_muamalat.model.LogActivityUser;
import com.example.technicaltest_muamalat.model.User;
import com.example.technicaltest_muamalat.repository.LogActivityUserRepository;
import com.example.technicaltest_muamalat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class AuthService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtProvider jwtProvider;

    private LogActivityUserRepository logActivityUserRepository;

    @Autowired
    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtProvider jwtProvider,
                       LogActivityUserRepository logActivityUserRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.logActivityUserRepository = logActivityUserRepository;
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private Boolean isPasswordMatch(String rawPassword, String encodedPassword) { return passwordEncoder.matches(rawPassword, encodedPassword);}

    @Transactional
    public ResponseEntity register(RegisterRequest registerRequest) {
        if (userRepository.findUserByUsername(registerRequest.getUsername()) != null) {
            return new ResponseEntity(DefaultResponse.res(StatusConstant.BAD_REQUEST, "Username already used"), HttpStatus.BAD_REQUEST);
        }

        if (userRepository.findUserByEmail(registerRequest.getEmail()) != null) {
            return new ResponseEntity(DefaultResponse.res(StatusConstant.BAD_REQUEST, "Email already used"), HttpStatus.BAD_REQUEST);
        }


        User user = mapUserForRegister(registerRequest);
        userRepository.save(user);
        return new ResponseEntity(DefaultResponse.res(StatusConstant.OK, "Registration successfully"), HttpStatus.OK);
    }

    private User mapUserForRegister(RegisterRequest registerRequest) {
        User user = new User();
        user.setNamaLengkap(registerRequest.getNamaLengkap());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodePassword(registerRequest.getPassword()));
        user.setPhone(registerRequest.getPhone());
        user.setUsername(registerRequest.getUsername());
        user.setRole(registerRequest.getRole());
        return user;
    }

    @Transactional
    public ResponseEntity login(LoginRequest loginRequest) {
        User isExisted = userRepository.findUserByEmail(loginRequest.getEmail());
        if(isExisted == null){
            return new ResponseEntity(DefaultResponse.res(StatusConstant.NOT_FOUND, "Account not found", ""), HttpStatus.NOT_FOUND);
        }
        if(!isPasswordMatch(loginRequest.getPassword(), isExisted.getPassword())){
            return new ResponseEntity(DefaultResponse.res(StatusConstant.BAD_REQUEST, "Password incorrect", ""), HttpStatus.BAD_REQUEST);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(isExisted.getUsername(),
                        loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        LoginResponse userResponse = new LoginResponse(isExisted, jwtProvider.generateToken(authentication));
        LogActivityUser logActivityUser = new LogActivityUser();
        Date date = new Date();
        logActivityUser.setCreateDate(date);
        logActivityUser.setTipeLogId(1);
        logActivityUser.setUserId(isExisted.getId());
        logActivityUserRepository.save(logActivityUser);
        return new ResponseEntity(DefaultResponse.res(StatusConstant.OK, "Login successfully", userResponse), HttpStatus.OK);
    }



}
