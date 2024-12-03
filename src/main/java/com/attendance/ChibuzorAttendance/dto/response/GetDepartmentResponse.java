package com.attendance.ChibuzorAttendance.dto.response;

import com.attendance.ChibuzorAttendance.data.models.Department;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetDepartmentResponse {
    private Department department;
    private String message;
}
