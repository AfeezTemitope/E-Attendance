package com.attendance.ChibuzorAttendance.service;

import com.attendance.ChibuzorAttendance.Exception.DepartmentNotFoundException;
import com.attendance.ChibuzorAttendance.Exception.DepartmentAlreadyExistException;
import com.attendance.ChibuzorAttendance.Exception.WrongPasswordException;
import com.attendance.ChibuzorAttendance.dto.request.LoginDepartmentRequest;
import com.attendance.ChibuzorAttendance.dto.response.CreateDepartmentResponse;
import com.attendance.ChibuzorAttendance.dto.request.CreateDepartmentRequest;
import com.attendance.ChibuzorAttendance.dto.response.LoginResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = {"/db/data.sql"})
class DepartmentServiceImplTest {

    @Autowired
    private DepartmentService attendanceDepartmentService;

    @Test
    void testThatDepartmentIsCreated() throws DepartmentNotFoundException, DepartmentAlreadyExistException {
    CreateDepartmentRequest createAttendanceUser = new CreateDepartmentRequest();
    createAttendanceUser.setDepartmentName("Chibuzor");
    createAttendanceUser.setPassword("Chibuzor");

    CreateDepartmentResponse response = attendanceDepartmentService.createDepartment(createAttendanceUser);
    assertNotNull(response);
    assertEquals("done", response.getMessage());
    }

    @Test
    void testThatDuplicateDepartmentNameThrowsException() {
        CreateDepartmentRequest createAttendanceUser = new CreateDepartmentRequest();
        createAttendanceUser.setDepartmentName("Titanic");
        createAttendanceUser.setPassword("17823");

        assertThrows(DepartmentNotFoundException.class, () -> {
            attendanceDepartmentService.createDepartment(createAttendanceUser);
            attendanceDepartmentService.createDepartment(createAttendanceUser);
        });
    }

    @Test
    public void testThatDepartmentCanBeLoggedIn() throws DepartmentNotFoundException, WrongPasswordException {
        LoginDepartmentRequest request = new LoginDepartmentRequest();
        request.setDepartment("Mavricks");
        request.setPassword("234");
        LoginResponse response = attendanceDepartmentService.loginDepartment(request);
        assertEquals("Login successful", response.getMessage());
    }

}