package com.example.technicaltest_muamalat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogRequest {
    private LocalDateTime crateDate;
    private Integer tipeLogId;
    private Integer userId;
}
