package com.example.technicaltest_muamalat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogActivityUserDto {
    private Date createDate;
    private Integer tipeLogId;
    private Integer userId;

}
