package com.attendance.ChibuzorAttendance.service;

import com.attendance.ChibuzorAttendance.Exception.AttendanceUserException;
import com.attendance.ChibuzorAttendance.dto.CreateAttendanceResponse;
import com.attendance.ChibuzorAttendance.dto.CreateAttendanceUser;

public interface AttendanceUserService {
    CreateAttendanceResponse createAUser(CreateAttendanceUser createAttendanceUser) throws AttendanceUserException;
}
