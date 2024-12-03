package com.attendance.ChibuzorAttendance.service;

import com.attendance.ChibuzorAttendance.Exception.AttendanceUserException;
import com.attendance.ChibuzorAttendance.dto.CreateAttendanceSheet;
import com.attendance.ChibuzorAttendance.dto.CreateAttendanceSheetResponse;

public interface AttendanceSheetService {
    CreateAttendanceSheetResponse createAttendanceSheet(CreateAttendanceSheet createAttendanceSheet, Long id) throws AttendanceUserException;
}
