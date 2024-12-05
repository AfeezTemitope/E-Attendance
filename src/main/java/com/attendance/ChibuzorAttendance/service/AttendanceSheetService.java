package com.attendance.ChibuzorAttendance.service;

import com.attendance.ChibuzorAttendance.Exception.AttendanceSheetNotFoundException;
import com.attendance.ChibuzorAttendance.Exception.AttendeeNotFoundException;
import com.attendance.ChibuzorAttendance.Exception.DepartmentNotFoundException;
import com.attendance.ChibuzorAttendance.dto.request.CreateAttendanceSheet;
import com.attendance.ChibuzorAttendance.dto.request.DeleteSheetRequest;
import com.attendance.ChibuzorAttendance.dto.request.UpdateSheetRequest;
import com.attendance.ChibuzorAttendance.dto.response.CreateAttendanceSheetResponse;
import com.attendance.ChibuzorAttendance.dto.response.DeleteSheetResponse;
import com.attendance.ChibuzorAttendance.dto.response.UpdateSheetResponse;

public interface AttendanceSheetService {
    CreateAttendanceSheetResponse createAttendanceSheet(CreateAttendanceSheet createAttendanceSheet) throws DepartmentNotFoundException;

    UpdateSheetResponse updateAttendanceSheet(UpdateSheetRequest request) throws AttendeeNotFoundException;

    DeleteSheetResponse deleteSheet(DeleteSheetRequest request) throws AttendanceSheetNotFoundException;
}


