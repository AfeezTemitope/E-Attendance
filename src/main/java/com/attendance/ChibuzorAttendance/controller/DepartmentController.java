package com.attendance.ChibuzorAttendance.controller;

import com.attendance.ChibuzorAttendance.Exception.GenericException;
import com.attendance.ChibuzorAttendance.dto.request.CreateDepartmentRequest;
import com.attendance.ChibuzorAttendance.dto.request.LoginDepartmentRequest;
import com.attendance.ChibuzorAttendance.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("create/department")
    public ResponseEntity<?> createDepartment(@RequestBody CreateDepartmentRequest request) {
        try{
            return new ResponseEntity<>(departmentService.createDepartment(request), HttpStatus.CREATED);
        }catch (GenericException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("login/department")
    public ResponseEntity<?> loginDepartment(@RequestBody LoginDepartmentRequest request) {
        try{
            return new ResponseEntity<>(departmentService.loginDepartment(request), HttpStatus.OK);
        }catch (GenericException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}



