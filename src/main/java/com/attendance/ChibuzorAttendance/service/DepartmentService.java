package com.attendance.ChibuzorAttendance.service;

import com.attendance.ChibuzorAttendance.Exception.DepartmentNotFoundException;
import com.attendance.ChibuzorAttendance.Exception.DepartmentAlreadyExistException;
import com.attendance.ChibuzorAttendance.Exception.WrongPasswordException;
import com.attendance.ChibuzorAttendance.dto.request.LoginDepartmentRequest;
import com.attendance.ChibuzorAttendance.dto.response.CreateDepartmentResponse;
import com.attendance.ChibuzorAttendance.dto.request.CreateDepartmentRequest;
import com.attendance.ChibuzorAttendance.dto.response.GetDepartmentResponse;
import com.attendance.ChibuzorAttendance.dto.response.LoginResponse;

public interface DepartmentService {
    CreateDepartmentResponse createDepartment(CreateDepartmentRequest createAttendanceUser) throws DepartmentNotFoundException, DepartmentAlreadyExistException;

    GetDepartmentResponse getDepartment(String departmentName) throws DepartmentNotFoundException;

    LoginResponse loginDepartment(LoginDepartmentRequest request) throws DepartmentNotFoundException, WrongPasswordException;
}
