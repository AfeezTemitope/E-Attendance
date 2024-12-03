package com.attendance.ChibuzorAttendance.Exception;

public class AttendanceUserException extends Exception{
    public AttendanceUserException(String userNotFound) {
        super(userNotFound);
    }
}
