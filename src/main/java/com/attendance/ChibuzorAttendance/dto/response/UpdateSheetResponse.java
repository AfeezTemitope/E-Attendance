package com.attendance.ChibuzorAttendance.dto.response;


import com.attendance.ChibuzorAttendance.data.models.Attendee;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UpdateSheetResponse {
    private String message;
    private Long id;
}
