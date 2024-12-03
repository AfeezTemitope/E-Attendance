package com.attendance.ChibuzorAttendance.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class CreateAttendanceUser {

    private String username;
    private String password;
    private Long id;

}
