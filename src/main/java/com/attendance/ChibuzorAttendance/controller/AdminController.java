package com.attendance.ChibuzorAttendance.controller;

import com.attendance.ChibuzorAttendance.Exception.GenericException;
import com.attendance.ChibuzorAttendance.Exception.UsernameAlreadyTakenException;
import com.attendance.ChibuzorAttendance.dto.request.LoginRequest;
import com.attendance.ChibuzorAttendance.dto.request.SignUpRequest;
import com.attendance.ChibuzorAttendance.service.AdminService;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            return new ResponseEntity<>(adminService.login(request),HttpStatus.OK);
        }catch (GenericException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUpAdmin(@RequestBody SignUpRequest request) {
        try{
            return new ResponseEntity<>(adminService.signUp(request), HttpStatus.ACCEPTED);
        }catch (UsernameAlreadyTakenException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
