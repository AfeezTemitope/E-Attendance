package com.attendance.ChibuzorAttendance.service;

import com.attendance.ChibuzorAttendance.Exception.AttendanceSheetNotFoundException;
import com.attendance.ChibuzorAttendance.Exception.AttendeeNotFoundException;
import com.attendance.ChibuzorAttendance.Exception.DepartmentNotFoundException;
import com.attendance.ChibuzorAttendance.dto.request.CreateAttendanceSheet;
import com.attendance.ChibuzorAttendance.dto.request.DeleteSheetRequest;
import com.attendance.ChibuzorAttendance.dto.request.UpdateSheetRequest;
import com.attendance.ChibuzorAttendance.dto.response.CreateAttendanceSheetResponse;
import com.attendance.ChibuzorAttendance.dto.response.DeleteSheetResponse;
import com.attendance.ChibuzorAttendance.dto.response.UpdateSheetResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
//@Sql(scripts = {"/db/data.sql"})
@Slf4j
public class AttendanceSheetTest {
    @Autowired
    private AttendanceSheetService attendanceSheetService;

    @Test
    void testThatAttendanceSheetCanBeCreated() throws DepartmentNotFoundException {

        CreateAttendanceSheet createAttendanceSheet = createAttendanceSheet();
        CreateAttendanceSheetResponse createdSheet = attendanceSheetService.createAttendanceSheet(createAttendanceSheet);
        assertNotNull(createdSheet);

        assertEquals(2,createdSheet.getAttendees().size());
        assertEquals("sheet created", createdSheet.getMessage());

    }

    private static CreateAttendanceSheet createAttendanceSheet() {
        CreateAttendanceSheet createAttendanceSheet = new CreateAttendanceSheet();
        createAttendanceSheet.setDate(LocalDateTime.now());
        String departmentName = "Titans";
        createAttendanceSheet.setDepartmentName(departmentName);
        return createAttendanceSheet;
    }

    @Test
    public void testThatAttendanceSheetCanBeUpdated() throws DepartmentNotFoundException, AttendeeNotFoundException {
        CreateAttendanceSheet createAttendanceSheet = createAttendanceSheet();
        CreateAttendanceSheetResponse createdSheet = attendanceSheetService.createAttendanceSheet(createAttendanceSheet);
        UpdateSheetRequest request = new UpdateSheetRequest();
        request.setPresent(true);
        request.setAttendeeId(createdSheet.getAttendees().get(1).getId());
        UpdateSheetResponse response = attendanceSheetService.updateAttendanceSheet(request);
        assertEquals(response.getMessage(), "sheet updated");
        assertEquals(response.getId(), createdSheet.getAttendees().get(1).getId());
    }


    @Test
    public void testThatSheetCanBeDeleted() throws DepartmentNotFoundException, AttendanceSheetNotFoundException {
        CreateAttendanceSheet createAttendanceSheet = createAttendanceSheet();
        CreateAttendanceSheetResponse createdSheet = attendanceSheetService.createAttendanceSheet(createAttendanceSheet);
        DeleteSheetRequest request = new DeleteSheetRequest();
        request.setId(createdSheet.getId());
        DeleteSheetResponse response = attendanceSheetService.deleteSheet(request);
        assertEquals(response.getMessage(), "Sheet Deleted Successfully");

    }
}

