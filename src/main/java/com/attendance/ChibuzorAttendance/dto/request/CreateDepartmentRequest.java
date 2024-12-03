package com.attendance.ChibuzorAttendance.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class CreateDepartmentRequest {
    private Long id;
    private String departmentName;
    private String password;
}
