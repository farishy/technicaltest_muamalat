package com.example.technicaltest_muamalat.service;

import com.example.technicaltest_muamalat.constant.StatusConstant;
import com.example.technicaltest_muamalat.dto.LogActivityUserDto;
import com.example.technicaltest_muamalat.model.DefaultResponse;
import com.example.technicaltest_muamalat.model.LogActivityUser;
import com.example.technicaltest_muamalat.repository.LogActivityUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogActivityUserService {
    private LogActivityUserRepository logActivityUserRepository;

    @Autowired
    public LogActivityUserService(LogActivityUserRepository logActivityUserRepository,
                          AuthService authService) {
        this.logActivityUserRepository = logActivityUserRepository;
//        this.authService = authService;
    }

    public ResponseEntity addLogActivityUser(LogActivityUserDto logActivityUserDto) {
        LogActivityUser logActivityUser = mapLogActivityUser(logActivityUserDto);
        logActivityUserRepository.save(logActivityUser);
        return new ResponseEntity(DefaultResponse.res(StatusConstant.OK, "Log has been added successfully"), HttpStatus.OK);
    }

    private LogActivityUser mapLogActivityUser(LogActivityUserDto logActivityUserDto) {
        LogActivityUser logActivityUser = new LogActivityUser();
        logActivityUser.setCreateDate(logActivityUserDto.getCreateDate());
        logActivityUser.setTipeLogId(logActivityUserDto.getTipeLogId());
        logActivityUser.setUserId(logActivityUserDto.getUserId());
        return logActivityUser;
    }
}
