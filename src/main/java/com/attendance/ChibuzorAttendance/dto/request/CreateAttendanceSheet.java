package com.attendance.ChibuzorAttendance.dto.request;

import com.attendance.ChibuzorAttendance.data.models.Department;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter

public class CreateAttendanceSheet {
    private LocalDateTime date;
    private String departmentName;

}
