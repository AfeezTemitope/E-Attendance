package com.attendance.ChibuzorAttendance.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateAttendeeRequest {
    private Long attendeeId;
    private String firstName;
    private String lastName;
    private String secretId;
    private String signIn;
    private boolean present;
    private String departmentName;

}
