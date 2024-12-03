package com.attendance.ChibuzorAttendance.service;

import com.attendance.ChibuzorAttendance.dto.request.CreateAttendeeRequest;
import com.attendance.ChibuzorAttendance.dto.response.RegisterAttendeeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AttendeeServiceTest {


    @Autowired
    private AttendeeService attendanceSheetService;

    @Test
    public void testThatAnAttendeeIsCreated() {
        CreateAttendeeRequest request = new CreateAttendeeRequest();
        request.setFirstName("Jane");
        request.setLastName("Doe");
        request.setSecretId("janD");
        RegisterAttendeeResponse response = attendanceSheetService.registerAttendee(request);
        assertNotNull(response);
        assertEquals("Successful", response.getMessage());
    }


}