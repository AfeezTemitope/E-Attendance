package com.attendance.ChibuzorAttendance.Exception;

public class DepartmentAlreadyExistException extends GenericException {
    public DepartmentAlreadyExistException(String userAlreadyExist) {
        super(userAlreadyExist);
    }
}
