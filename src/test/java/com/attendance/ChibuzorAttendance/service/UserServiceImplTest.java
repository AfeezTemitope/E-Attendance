package com.attendance.ChibuzorAttendance.service;

import com.attendance.ChibuzorAttendance.Exception.AttendanceUserException;
import com.attendance.ChibuzorAttendance.dto.CreateAttendanceResponse;
import com.attendance.ChibuzorAttendance.dto.CreateAttendanceSheet;
import com.attendance.ChibuzorAttendance.dto.CreateAttendanceSheetResponse;
import com.attendance.ChibuzorAttendance.dto.CreateAttendanceUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private AttendanceUserService attendanceUserService;
    @Autowired
    private AttendanceSheetService attendanceSheetService;

    @Test
    void testThatAttendanceUserIsCreated() throws AttendanceUserException {
    CreateAttendanceUser createAttendanceUser = new CreateAttendanceUser();
    createAttendanceUser.setUsername("Chibuzor");
    createAttendanceUser.setPassword("Chibuzor");

    CreateAttendanceResponse response = attendanceUserService.createAUser(createAttendanceUser);
    assertNotNull(response);
    assertEquals("done", response.getMessage());
    }

    @Test
    void testThatDuplicateUsernameThrowsException() {
        CreateAttendanceUser createAttendanceUser = new CreateAttendanceUser();
        createAttendanceUser.setUsername("OmoBuruku");
        createAttendanceUser.setPassword("securePassword123");

        assertThrows(AttendanceUserException.class, () -> {
            attendanceUserService.createAUser(createAttendanceUser);
            attendanceUserService.createAUser(createAttendanceUser);
        });
    }
    @Test
    void testThatAttendanceSheetCanBeCreated() throws AttendanceUserException {
        CreateAttendanceUser createAttendanceUser = new CreateAttendanceUser();
        createAttendanceUser.setUsername("departmentAdmin");
        createAttendanceUser.setPassword("securePassword123");
        attendanceUserService.createAUser(createAttendanceUser);

       CreateAttendanceSheet createAttendanceSheet = new CreateAttendanceSheet();
       createAttendanceSheet.setDate(LocalDateTime.now());

        CreateAttendanceSheetResponse createdSheet = attendanceSheetService.createAttendanceSheet(createAttendanceSheet, createAttendanceUser.getId());
        assertNotNull(createdSheet);
        assertEquals("sheet created", createdSheet.getMessage());

    }
}