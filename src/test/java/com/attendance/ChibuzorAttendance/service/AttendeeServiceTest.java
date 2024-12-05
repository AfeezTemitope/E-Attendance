package com.attendance.ChibuzorAttendance.service;

import com.attendance.ChibuzorAttendance.Exception.AttendeeNotFoundException;
import com.attendance.ChibuzorAttendance.Exception.SecretIdAlreadyExistException;
import com.attendance.ChibuzorAttendance.dto.request.CreateAttendeeRequest;
import com.attendance.ChibuzorAttendance.dto.request.DeleteAttendeeRequest;
import com.attendance.ChibuzorAttendance.dto.request.GetAttendeeByDepartment;
import com.attendance.ChibuzorAttendance.dto.response.DeleteAttendeeResponse;
import com.attendance.ChibuzorAttendance.dto.request.UpdateAttendeeRequest;
import com.attendance.ChibuzorAttendance.dto.response.GetAttendeeResponse;
import com.attendance.ChibuzorAttendance.dto.response.RegisterAttendeeResponse;
import com.attendance.ChibuzorAttendance.dto.response.UpdateAttendeeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Sql(scripts = {"/db/data.sql"})
public class AttendeeServiceTest {


    @Autowired
    private AttendeeService attendanceService;

    @Test
    public void testThatAnAttendeeIsCreated() throws SecretIdAlreadyExistException {
        CreateAttendeeRequest request = createAttendeeRequest();
        RegisterAttendeeResponse response = attendanceService.registerAttendee(request);
        assertNotNull(response);
        assertEquals("Successful", response.getMessage());
    }

    private static CreateAttendeeRequest createAttendeeRequest() {
        CreateAttendeeRequest request = new CreateAttendeeRequest();
        request.setFirstName("Jane");
        request.setLastName("Doe");
        request.setSecretId("janD");
        request.setDepartmentName("Titans");
        return request;
    }

    @Test
    public void testThatAnAttendeeIsUpdated() throws SecretIdAlreadyExistException, AttendeeNotFoundException {
        CreateAttendeeRequest request = createAttendeeRequest();
        RegisterAttendeeResponse response = attendanceService.registerAttendee(request);
        UpdateAttendeeRequest update = new UpdateAttendeeRequest();
        update.setAttendeeId(response.getId());
        update.setFirstName("Musa");
        update.setLastName("Rex");
        update.setSecretId("T-Rex");
        update.setPresent(true);
        UpdateAttendeeResponse updateResponse = attendanceService.updateAttendee(update);
        assertEquals(updateResponse.getAttendeeId(), response.getId());
        assertEquals("Successful", updateResponse.getMessage());
    }


    @Test
    public  void testThatAttendeeCanBeDeleted() throws SecretIdAlreadyExistException, AttendeeNotFoundException {
        CreateAttendeeRequest request = createAttendeeRequest();
        RegisterAttendeeResponse response = attendanceService.registerAttendee(request);
        DeleteAttendeeRequest delete = new DeleteAttendeeRequest();
        delete.setAttendeeId(response.getId());
        DeleteAttendeeResponse deleteResponse = attendanceService.deleteAttendee(delete);
        assertEquals("Successful",deleteResponse.getMessage());
    }

    @Test
    public void getAllAttendeeByDepartmentName() throws SecretIdAlreadyExistException {
        CreateAttendeeRequest request = createAttendeeRequest();
        RegisterAttendeeResponse response = attendanceService.registerAttendee(request);
        CreateAttendeeRequest request2 = createAttendeeRequest();
        request2.setSecretId("121345");
        request2.setDepartmentName("TITANS");
        attendanceService.registerAttendee(request2);

        GetAttendeeByDepartment getRequest = new GetAttendeeByDepartment();
        getRequest.setDepartmentName("TItans");
        GetAttendeeResponse getResponse = attendanceService.getAllByDepartment(getRequest);
        assertEquals("Successful",getResponse.getMessage());
        assertEquals(2,getResponse.getAttendees().size());


    }

}