package com.attendance.ChibuzorAttendance.controller;

import com.attendance.ChibuzorAttendance.Exception.GenericException;
import com.attendance.ChibuzorAttendance.dto.request.*;
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

    @PostMapping("create/sheet")
    public ResponseEntity<?> createAttendanceSheet(@RequestBody CreateAttendanceSheet request) {
        try{
            return new ResponseEntity<>(attendanceSheetService.createAttendanceSheet(request),HttpStatus.CREATED);
        }catch (GenericException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("update/sheet")
    public ResponseEntity<?> updateAttendanceSheet(@RequestBody UpdateSheetRequest request) {
        try{
            return new ResponseEntity<>(attendanceSheetService.updateAttendanceSheet(request),HttpStatus.ACCEPTED);
        }catch (GenericException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/sheet")
    public ResponseEntity<?> deleteAttendanceSheet(@RequestBody DeleteSheetRequest request) {
        try{
            return new ResponseEntity<>(attendanceSheetService.deleteSheet(request),HttpStatus.OK);
        }catch (GenericException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
