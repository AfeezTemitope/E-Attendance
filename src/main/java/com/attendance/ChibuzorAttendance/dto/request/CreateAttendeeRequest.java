package com.attendance.ChibuzorAttendance.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateAttendeeRequest {
    private String firstName;
    private String lastName;
    private String secretId;

}
