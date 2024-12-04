package com.attendance.ChibuzorAttendance.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDepartmentRequest {
    private String password;
    private String department;

}
