package com.attendance.ChibuzorAttendance.Exception;

public class DepartmentNotFoundException extends GenericException{
    public DepartmentNotFoundException(String userNotFound) {
        super(userNotFound);
    }
}
