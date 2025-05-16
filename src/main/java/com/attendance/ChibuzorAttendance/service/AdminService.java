package com.attendance.ChibuzorAttendance.service;

import com.attendance.ChibuzorAttendance.Exception.GenericException;
import com.attendance.ChibuzorAttendance.Exception.WrongPasswordException;
import com.attendance.ChibuzorAttendance.dto.request.LoginRequest;
import com.attendance.ChibuzorAttendance.dto.request.SignUpRequest;
import com.attendance.ChibuzorAttendance.dto.response.LoginResponse;
import com.attendance.ChibuzorAttendance.dto.response.SignUpResponse;

public interface AdminService {
    LoginResponse login(LoginRequest request) throws GenericException;
    SignUpResponse signUp(SignUpRequest request);
}
