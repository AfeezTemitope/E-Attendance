package com.attendance.ChibuzorAttendance.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateAttendeeResponse {
    private Long attendeeId;
    private String message;
}
