package com.attendance.ChibuzorAttendance.service;

import com.attendance.ChibuzorAttendance.Exception.DepartmentNotFoundException;
import com.attendance.ChibuzorAttendance.data.models.AttendanceSheet;
import com.attendance.ChibuzorAttendance.data.repositories.AttendanceSheetRepository;
import com.attendance.ChibuzorAttendance.dto.request.CreateAttendanceSheet;
import com.attendance.ChibuzorAttendance.dto.response.CreateAttendanceSheetResponse;
import com.attendance.ChibuzorAttendance.dto.response.GetDepartmentResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class SheetServiceImpl implements AttendanceSheetService {

    private final AttendanceSheetRepository attendanceSheetRepository;

    private final DepartmentService departmentService;

    @Override
    @Transactional
    public  CreateAttendanceSheetResponse createAttendanceSheet(CreateAttendanceSheet createAttendanceSheet) throws DepartmentNotFoundException {
        GetDepartmentResponse foundDepartment = departmentService.getDepartment(createAttendanceSheet.getDepartmentName());
            AttendanceSheet attendanceSheet = new AttendanceSheet();
            attendanceSheet.setDate(LocalDateTime.now());
            attendanceSheet.setDepartment(foundDepartment.getDepartment());
            attendanceSheet  = attendanceSheetRepository.save(attendanceSheet);

            CreateAttendanceSheetResponse response = new CreateAttendanceSheetResponse();
            response.setId(attendanceSheet.getId());
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