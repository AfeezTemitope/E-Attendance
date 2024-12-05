package com.attendance.ChibuzorAttendance.dto.response;

import com.attendance.ChibuzorAttendance.data.models.AttendanceSheet;
import com.attendance.ChibuzorAttendance.data.models.Attendee;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CreateAttendanceSheetResponse {
    private Long id;
    private String message;
    private List<Attendee> attendees;
}
