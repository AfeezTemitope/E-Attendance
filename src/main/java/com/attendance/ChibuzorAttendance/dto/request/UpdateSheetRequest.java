package com.attendance.ChibuzorAttendance.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateSheetRequest {
    private Long attendeeId;
    private boolean isPresent;
}
