package com.attendance.ChibuzorAttendance.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateDepartmentResponse {
    private Long id;
    private String departmentName;
    private String message;
}
