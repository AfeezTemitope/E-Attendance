package com.attendance.ChibuzorAttendance.service;

import com.attendance.ChibuzorAttendance.Exception.SecretIdAlreadyExistException;
import com.attendance.ChibuzorAttendance.dto.request.CreateAttendeeRequest;
import com.attendance.ChibuzorAttendance.dto.response.RegisterAttendeeResponse;

public interface AttendeeService {

    RegisterAttendeeResponse registerAttendee(CreateAttendeeRequest request) throws SecretIdAlreadyExistException;
}

