package com.attendance.ChibuzorAttendance.service;

import com.attendance.ChibuzorAttendance.Exception.AttendeeNotFoundException;
import com.attendance.ChibuzorAttendance.Exception.SecretIdAlreadyExistException;
import com.attendance.ChibuzorAttendance.dto.request.CreateAttendeeRequest;
import com.attendance.ChibuzorAttendance.dto.request.DeleteAttendeeRequest;
import com.attendance.ChibuzorAttendance.dto.request.GetAttendeeByDepartment;
import com.attendance.ChibuzorAttendance.dto.request.UpdateAttendeeRequest;
import com.attendance.ChibuzorAttendance.dto.response.DeleteAttendeeResponse;
import com.attendance.ChibuzorAttendance.dto.response.GetAttendeeResponse;
import com.attendance.ChibuzorAttendance.dto.response.RegisterAttendeeResponse;
import com.attendance.ChibuzorAttendance.dto.response.UpdateAttendeeResponse;

public interface AttendeeService {

    RegisterAttendeeResponse registerAttendee(CreateAttendeeRequest request) throws SecretIdAlreadyExistException;

    UpdateAttendeeResponse updateAttendee(UpdateAttendeeRequest update) throws AttendeeNotFoundException;

    DeleteAttendeeResponse deleteAttendee(DeleteAttendeeRequest request) throws AttendeeNotFoundException;

    GetAttendeeResponse getAllByDepartment(GetAttendeeByDepartment getRequest);
}

