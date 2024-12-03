package com.attendance.ChibuzorAttendance.dto;

import com.attendance.ChibuzorAttendance.data.models.Attendee;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter

public class CreateAttendanceSheet {
    private LocalDateTime date;
    private List<Attendee> attendees;

}
