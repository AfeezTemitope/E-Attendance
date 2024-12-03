package com.attendance.ChibuzorAttendance.Exception;

public class SecretIdAlreadyExistException extends GenericException {
    public SecretIdAlreadyExistException(String secretIdAlreadyExist) {
        super(secretIdAlreadyExist);
    }
}
