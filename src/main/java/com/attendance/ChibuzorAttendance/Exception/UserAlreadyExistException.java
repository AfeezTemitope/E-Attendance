package com.attendance.ChibuzorAttendance.Exception;

public class UserAlreadyExistException extends GenericException {
    public UserAlreadyExistException(String userAlreadyExist) {
        super(userAlreadyExist);
    }
}
