package com.attendance.ChibuzorAttendance.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpRequest {
    private String userName;
    private String password;
    private String email;

}
