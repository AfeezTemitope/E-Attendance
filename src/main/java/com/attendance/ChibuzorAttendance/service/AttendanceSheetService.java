package com.attendance.ChibuzorAttendance.service;

import com.attendance.ChibuzorAttendance.Exception.DepartmentNotFoundException;
import com.attendance.ChibuzorAttendance.dto.request.CreateAttendanceSheet;
import com.attendance.ChibuzorAttendance.dto.response.CreateAttendanceSheetResponse;

public interface AttendanceSheetService {
    CreateAttendanceSheetResponse createAttendanceSheet(CreateAttendanceSheet createAttendanceSheet) throws DepartmentNotFoundException;
}
