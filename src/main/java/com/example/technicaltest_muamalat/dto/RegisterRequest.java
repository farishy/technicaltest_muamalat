package com.example.technicaltest_muamalat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String namaLengkap;
    private String email;
    private String password;
    private String phone;
    private String username;
    private String role;
}
