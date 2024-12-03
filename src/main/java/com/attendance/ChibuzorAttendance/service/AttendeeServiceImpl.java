package com.attendance.ChibuzorAttendance.service;

import com.attendance.ChibuzorAttendance.Exception.SecretIdAlreadyExistException;
import com.attendance.ChibuzorAttendance.data.models.Attendee;
import com.attendance.ChibuzorAttendance.data.repositories.AttendeeRepository;
import com.attendance.ChibuzorAttendance.dto.request.CreateAttendeeRequest;
import com.attendance.ChibuzorAttendance.dto.response.RegisterAttendeeResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
@AllArgsConstructor
public class AttendeeServiceImpl implements AttendeeService{

    private final AttendeeRepository attendeeRepo;

    @Override
    public RegisterAttendeeResponse registerAttendee(CreateAttendeeRequest request) throws SecretIdAlreadyExistException {
        if(attendeeRepo.existBySecretIdIgnoreCase(request.getSecretId())){
            throw new SecretIdAlreadyExistException("Secret Id Already Exist");
        }
        Attendee attendee = new Attendee();
        attendee.setFirstName(request.getFirstName());
        attendee.setLastName(request.getLastName());
        attendee.setSecretId(request.getSecretId());
        attendee = attendeeRepo.save(attendee);
        RegisterAttendeeResponse response = new RegisterAttendeeResponse();
        response.setId(attendee.getId());
        response.setMessage("Successful");
        return response;
    }
}
