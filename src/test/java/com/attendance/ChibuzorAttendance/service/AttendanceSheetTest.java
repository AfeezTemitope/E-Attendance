package com.attendance.ChibuzorAttendance.service;

import com.attendance.ChibuzorAttendance.Exception.DepartmentNotFoundException;
import com.attendance.ChibuzorAttendance.dto.request.CreateAttendanceSheet;
import com.attendance.ChibuzorAttendance.dto.response.CreateAttendanceSheetResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
//@Sql(scripts = {"/db/data.sql"})
public class AttendanceSheetTest {
    @Autowired
    private AttendanceSheetService attendanceSheetService;
    @Autowired
    private DepartmentService departmentService;
    @Test
    void testThatAttendanceSheetCanBeCreated() throws DepartmentNotFoundException {

        CreateAttendanceSheet createAttendanceSheet = new CreateAttendanceSheet();
        createAttendanceSheet.setDate(LocalDateTime.now());
        String departmentName = "Titans";
        createAttendanceSheet.setDepartmentName(departmentName);
        CreateAttendanceSheetResponse createdSheet = attendanceSheetService.createAttendanceSheet(createAttendanceSheet);
        assertNotNull(createdSheet);
        assertEquals("sheet created", createdSheet.getMessage());

    }

}

