package com.attendance.ChibuzorAttendance.controller;

import com.attendance.ChibuzorAttendance.Exception.DepartmentNotFoundException;
import com.attendance.ChibuzorAttendance.Exception.GenericException;
import com.attendance.ChibuzorAttendance.dto.request.CreateAttendeeRequest;
import com.attendance.ChibuzorAttendance.dto.request.CreateDepartmentRequest;
import com.attendance.ChibuzorAttendance.dto.request.LoginDepartmentRequest;
import com.attendance.ChibuzorAttendance.service.AttendanceSheetService;
import com.attendance.ChibuzorAttendance.service.AttendeeService;
import com.attendance.ChibuzorAttendance.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class AttendanceSheetController {
    @Autowired
    private AttendanceSheetService attendanceSheetService;

    @Autowired
    private AttendeeService attendeeService;
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("createDepartment/")
    public ResponseEntity<?> createDepartment(@RequestBody CreateDepartmentRequest request) {
        try{
            return new ResponseEntity<>(departmentService.createDepartment(request), HttpStatus.CREATED);
        }catch (GenericException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("registerAttendee/")
    public ResponseEntity<?> registerNewAttendee(@RequestBody CreateAttendeeRequest request) {
        try{
            return new ResponseEntity<>(attendeeService.registerAttendee(request), HttpStatus.CREATED);
        }catch (GenericException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("loginDepartment/")
    public ResponseEntity<?> getAllAttendanceSheets(@RequestBody LoginDepartmentRequest request) {
        try{
            return new ResponseEntity<>(departmentService.loginDepartment(request), HttpStatus.OK);
        }catch (GenericException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
