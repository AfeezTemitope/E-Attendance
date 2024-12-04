package com.attendance.ChibuzorAttendance.service;

import com.attendance.ChibuzorAttendance.Exception.AttendeeNotFoundException;
import com.attendance.ChibuzorAttendance.Exception.SecretIdAlreadyExistException;
import com.attendance.ChibuzorAttendance.data.models.Attendee;
import com.attendance.ChibuzorAttendance.data.repositories.AttendeeRepository;
import com.attendance.ChibuzorAttendance.dto.request.CreateAttendeeRequest;
import com.attendance.ChibuzorAttendance.dto.request.DeleteAttendeeRequest;
import com.attendance.ChibuzorAttendance.dto.request.GetAttendeeByDepartment;
import com.attendance.ChibuzorAttendance.dto.request.UpdateAttendeeRequest;
import com.attendance.ChibuzorAttendance.dto.response.DeleteAttendeeResponse;
import com.attendance.ChibuzorAttendance.dto.response.GetAttendeeResponse;
import com.attendance.ChibuzorAttendance.dto.response.RegisterAttendeeResponse;
import com.attendance.ChibuzorAttendance.dto.response.UpdateAttendeeResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AttendeeServiceImpl implements AttendeeService{

    private final AttendeeRepository attendeeRepo;

    @Override

    public RegisterAttendeeResponse registerAttendee(CreateAttendeeRequest request) throws SecretIdAlreadyExistException {
        if(attendeeRepo.existsBySecretIdIgnoreCase(request.getSecretId())){
            throw new SecretIdAlreadyExistException("Secret Id Already Exist");
        }
        Attendee attendee = new Attendee();
        attendee.setFirstName(request.getFirstName());
        attendee.setLastName(request.getLastName());
        attendee.setSecretId(request.getSecretId());
        attendee.setDepartmentName(request.getDepartmentName());
        attendee = attendeeRepo.save(attendee);
        RegisterAttendeeResponse response = new RegisterAttendeeResponse();
        response.setId(attendee.getId());
        response.setMessage("Successful");
        return response;
    }

    @Override
    public UpdateAttendeeResponse updateAttendee(UpdateAttendeeRequest update) throws AttendeeNotFoundException {
        Attendee attendee = findAttendeeById(update.getAttendeeId());
        setAttendeeUpdates(update, attendee);
        Attendee updatedAttendee = attendeeRepo.save(attendee);
        UpdateAttendeeResponse response = new UpdateAttendeeResponse();
        response.setAttendeeId(updatedAttendee.getId());
        response.setMessage("Successful");
        return response;
    }

    @Override
    public DeleteAttendeeResponse deleteAttendee(DeleteAttendeeRequest request) throws AttendeeNotFoundException {
        Attendee attendee = findAttendeeById(request.getAttendeeId());
        attendeeRepo.delete(attendee);
        DeleteAttendeeResponse response = new DeleteAttendeeResponse();
        response.setMessage("Successful");
        return response;
    }

    @Override
    public GetAttendeeResponse getAllByDepartment(GetAttendeeByDepartment getRequest) {
        List<Attendee> relatedAttendees = attendeeRepo.findAllByDepartmentNameIgnoreCase(getRequest.getDepartment());
        GetAttendeeResponse response = new GetAttendeeResponse();
        response.setAttendees(relatedAttendees);
        response.setMessage("Successful");
        return response;

    }

    private static void setAttendeeUpdates(UpdateAttendeeRequest update, Attendee attendee) {
        attendee.setSignedInTime(LocalDateTime.now());
        if(update.getFirstName() != null){
            attendee.setFirstName(update.getFirstName());
        }
        if(update.getLastName() != null){
            attendee.setLastName(update.getLastName());
        }
        if(update.getSecretId() != null){
            attendee.setSecretId(update.getSecretId());
        }
        if(update.getDepartmentName() != null){
            attendee.setDepartmentName(update.getDepartmentName());
        }
        if(update.isPresent() != attendee.getIsPresent()){
            attendee.setIsPresent(update.isPresent());
        }
    }

    private Attendee findAttendeeById(Long update) throws AttendeeNotFoundException {
        return attendeeRepo.findById(update)
                .orElseThrow(() -> new AttendeeNotFoundException("Attendee Not Found"));
    }
}
