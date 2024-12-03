package com.attendance.ChibuzorAttendance.service;

import com.attendance.ChibuzorAttendance.Exception.DepartmentNotFoundException;
import com.attendance.ChibuzorAttendance.Exception.UserAlreadyExistException;
import com.attendance.ChibuzorAttendance.dto.response.CreateDepartmentResponse;
import com.attendance.ChibuzorAttendance.dto.request.CreateDepartmentRequest;
import com.attendance.ChibuzorAttendance.dto.response.GetDepartmentResponse;

public interface DepartmentService {
    CreateDepartmentResponse createDepartment(CreateDepartmentRequest createAttendanceUser) throws DepartmentNotFoundException, UserAlreadyExistException;

    GetDepartmentResponse getDepartment(String departmentName) throws DepartmentNotFoundException;
}
