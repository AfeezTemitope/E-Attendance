package com.attendance.ChibuzorAttendance.controller;

import com.attendance.ChibuzorAttendance.Exception.DepartmentNotFoundException;
import com.attendance.ChibuzorAttendance.Exception.GenericException;
import com.attendance.ChibuzorAttendance.dto.request.CreateAttendeeRequest;
import com.attendance.ChibuzorAttendance.dto.request.DeleteAttendeeRequest;
import com.attendance.ChibuzorAttendance.dto.request.GetAttendeeByDepartment;
import com.attendance.ChibuzorAttendance.dto.request.UpdateAttendeeRequest;
import com.attendance.ChibuzorAttendance.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;

    @PostMapping("register/attendee")
    public ResponseEntity<?> registerNewAttendee(@RequestBody CreateAttendeeRequest request) {
        try{
            return new ResponseEntity<>(attendeeService.registerAttendee(request), HttpStatus.CREATED);
        }catch (GenericException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("get/attendees")
    public ResponseEntity<?> getAttendeeByDepartment(@RequestBody GetAttendeeByDepartment request) {
        try{
            return new ResponseEntity<>(attendeeService.getAllByDepartment(request),HttpStatus.OK);
        }catch (DepartmentNotFoundException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("get/all/attendees")
    public ResponseEntity<?> getAllAttendee() {
            return new ResponseEntity<>(attendeeService.getAllAttendee(),HttpStatus.OK);
    }

    @PatchMapping("update/attendee")
    public ResponseEntity<?> updateAttendee(@RequestBody UpdateAttendeeRequest request) {
        try{
            return new ResponseEntity<>(attendeeService.updateAttendee( request),HttpStatus.ACCEPTED);
        }catch (GenericException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/attendee")
    public ResponseEntity<?> deleteAttendee(@RequestBody DeleteAttendeeRequest request) {
        try{
            return new ResponseEntity<>(attendeeService.deleteAttendee(request),HttpStatus.OK);
        }catch (GenericException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }




}
