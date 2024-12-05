package com.attendance.ChibuzorAttendance.service;

import com.attendance.ChibuzorAttendance.Exception.AttendanceSheetNotFoundException;
import com.attendance.ChibuzorAttendance.Exception.AttendeeNotFoundException;
import com.attendance.ChibuzorAttendance.Exception.DepartmentNotFoundException;
import com.attendance.ChibuzorAttendance.data.models.AttendanceSheet;
import com.attendance.ChibuzorAttendance.data.repositories.AttendanceSheetRepository;
import com.attendance.ChibuzorAttendance.dto.request.*;
import com.attendance.ChibuzorAttendance.dto.response.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class SheetServiceImpl implements AttendanceSheetService {

    private final AttendanceSheetRepository attendanceSheetRepository;

    private final AttendeeService attendeeService;

    @Override
    @Transactional
    public  CreateAttendanceSheetResponse createAttendanceSheet(CreateAttendanceSheet createAttendanceSheet) throws DepartmentNotFoundException {
        GetAttendeeByDepartment getAttendeeByDepartment = new GetAttendeeByDepartment();
        getAttendeeByDepartment.setDepartmentName(createAttendanceSheet.getDepartmentName());

        GetAttendeeResponse foundAttendees = attendeeService.getAllByDepartment(getAttendeeByDepartment);
        AttendanceSheet attendanceSheet = new AttendanceSheet();
        System.out.println(foundAttendees.getAttendees().toString());
            attendanceSheet.setDate(LocalDateTime.now());
            attendanceSheet.setDepartmentName(createAttendanceSheet.getDepartmentName());
            attendanceSheet.setAttendees(foundAttendees.getAttendees());
            attendanceSheet  = attendanceSheetRepository.save(attendanceSheet);

            CreateAttendanceSheetResponse response = new CreateAttendanceSheetResponse();
            response.setAttendees(attendanceSheet.getAttendees());
            response.setId(attendanceSheet.getId());
            response.setMessage("sheet created");
            return  response;

    }

    @Override
    public UpdateSheetResponse updateAttendanceSheet(UpdateSheetRequest request) throws AttendeeNotFoundException {
        UpdateAttendeeRequest update = new UpdateAttendeeRequest();
        update.setAttendeeId(request.getAttendeeId());
        update.setPresent(request.isPresent());
        UpdateAttendeeResponse updateResponse = attendeeService.updateAttendee(update);
        UpdateSheetResponse response = new UpdateSheetResponse();
        response.setId(updateResponse.getAttendeeId());
        response.setMessage("sheet updated");
        return response;
    }

    @Override
    public DeleteSheetResponse deleteSheet(DeleteSheetRequest request) throws AttendanceSheetNotFoundException {
        AttendanceSheet foundSheet = attendanceSheetRepository.findById(request.getId())
                .orElseThrow(() -> new AttendanceSheetNotFoundException("Sheet Not Found"));
        attendanceSheetRepository.delete(foundSheet);
        DeleteSheetResponse deleteSheetResponse = new DeleteSheetResponse();
        deleteSheetResponse.setMessage("Sheet Deleted Successfully");
        return  deleteSheetResponse;
    }


}

