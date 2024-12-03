package com.attendance.ChibuzorAttendance.service;

import com.attendance.ChibuzorAttendance.Exception.AttendanceUserException;
import com.attendance.ChibuzorAttendance.data.models.AttendanceSheet;
import com.attendance.ChibuzorAttendance.data.models.AttendanceUser;
import com.attendance.ChibuzorAttendance.data.repositories.AttendanceSheetRepository;
import com.attendance.ChibuzorAttendance.data.repositories.AttendanceUserRepository;
import com.attendance.ChibuzorAttendance.dto.CreateAttendanceSheet;
import com.attendance.ChibuzorAttendance.dto.CreateAttendanceSheetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class SheetServiceImpl implements AttendanceSheetService {
    @Autowired
    private AttendanceUserRepository attendanceUserRepository;
    @Autowired
    private AttendanceSheetRepository attendanceSheetRepository;

    @Override
    public CreateAttendanceSheetResponse createAttendanceSheet(CreateAttendanceSheet createAttendanceSheet, Long id) throws AttendanceUserException {
        AttendanceUser user = attendanceUserRepository.findById(id)
                .orElseThrow(() -> new AttendanceUserException("User not found"));

        AttendanceSheet attendanceSheet = new AttendanceSheet();
        attendanceSheet.setDate(LocalDateTime.now());
//        attendanceSheet.setDepartmentName(user.getUsername());
        attendanceSheet.setAttendees(createAttendanceSheet.getAttendees());
        user.getSheets().add(attendanceSheet);

        attendanceSheetRepository.save(attendanceSheet);
        attendanceUserRepository.save(user);

        CreateAttendanceSheetResponse response = new CreateAttendanceSheetResponse();
        response.setMessage("sheet created");

        return  response;


    }
}
/*
*     public AttendanceSheet createAttendanceSheet(Long userId, AttendanceSheet attendanceSheet) throws AttendanceUserException {
        AttendanceUser user = attendanceUserRepository.findById(userId)
                .orElseThrow(() -> new AttendanceUserException("User not found"));

        // Link the attendance sheet to the user
        attendanceSheet.setUser(user);
        user.getSheets().add(attendanceSheet);
        attendanceUserRepository.save(user);

        return attendanceSheet;
    }*/