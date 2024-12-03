package com.attendance.ChibuzorAttendance.service;

import com.attendance.ChibuzorAttendance.Exception.DepartmentNotFoundException;
import com.attendance.ChibuzorAttendance.dto.response.CreateDepartmentResponse;
import com.attendance.ChibuzorAttendance.dto.request.CreateDepartmentRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = {"/db/data.sql"})
class DepartmentServiceImplTest {

    @Autowired
    private DepartmentService attendanceUserService;

    @Test
    void testThatDepartmentIsCreated() throws DepartmentNotFoundException {
    CreateDepartmentRequest createAttendanceUser = new CreateDepartmentRequest();
    createAttendanceUser.setDepartmentName("Chibuzor");
    createAttendanceUser.setPassword("Chibuzor");

    CreateDepartmentResponse response = attendanceUserService.createDepartment(createAttendanceUser);
    assertNotNull(response);
    assertEquals("done", response.getMessage());
    }

    @Test
    void testThatDuplicateDepartmentNameThrowsException() {
        CreateDepartmentRequest createAttendanceUser = new CreateDepartmentRequest();
        createAttendanceUser.setDepartmentName("Titanic");
        createAttendanceUser.setPassword("17823");

        assertThrows(DepartmentNotFoundException.class, () -> {
            attendanceUserService.createDepartment(createAttendanceUser);
            attendanceUserService.createDepartment(createAttendanceUser);
        });
    }

}