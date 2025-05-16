package com.attendance.ChibuzorAttendance.service;

import com.attendance.ChibuzorAttendance.Exception.AttendeeNotFoundException;
import com.attendance.ChibuzorAttendance.Exception.DepartmentNotFoundException;
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

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AttendeeServiceImpl implements AttendeeService{

    private final AttendeeRepository attendeeRepo;

    private final DepartmentService departmentService;

    @Override

    public RegisterAttendeeResponse registerAttendee(CreateAttendeeRequest request) throws SecretIdAlreadyExistException, DepartmentNotFoundException {
        RegisterAttendeeResponse response = new RegisterAttendeeResponse();
        if(attendeeRepo.existsBySecretIdIgnoreCase(request.getSecretId()))
            throw new SecretIdAlreadyExistException("Secret Password Already Exist");
        else if (departmentExist(request.getDepartmentName())) {
            Attendee attendee = new Attendee();
            attendee.setFirstName(request.getFirstName());
            attendee.setLastName(request.getLastName());
            attendee.setSecretId(request.getSecretId());
            attendee.setDepartmentName(request.getDepartmentName());
            attendee = attendeeRepo.save(attendee);
            response.setId(attendee.getId());
            response.setMessage("Successful");
            return response;
        }
        throw new DepartmentNotFoundException("Department Not Found");
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
    public GetAttendeeResponse getAllByDepartment(GetAttendeeByDepartment getRequest) throws DepartmentNotFoundException {
        List<Attendee> relatedAttendees = attendeeRepo.findAllByDepartmentNameIgnoreCase(getRequest.getDepartmentName())
                .orElseThrow( () -> new DepartmentNotFoundException("No Attendee Found with This Department Name"));
        GetAttendeeResponse response = new GetAttendeeResponse();
        response.setAttendees(relatedAttendees);
        response.setMessage("Successful");
        return response;

    }

    @Override
    public GetAttendeeResponse getAllAttendee() {
        GetAttendeeResponse response = new GetAttendeeResponse();
        response.setMessage("Successful");
        response.setAttendees(attendeeRepo.findAll());
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
        if(update.isPresent() != attendee.isPresent()){
            attendee.setPresent(update.isPresent());
        }
    }

    private Attendee findAttendeeById(Long update) throws AttendeeNotFoundException {
        return attendeeRepo.findById(update)
                .orElseThrow(() -> new AttendeeNotFoundException("Attendee Not Found"));
    }
    private boolean departmentExist(String department) {

       try{
           departmentService.getDepartment(department);
           return true;
       }catch (DepartmentNotFoundException exception){
           return false;
       }
    }
    private boolean verifyNames(String firstName, String lastname){
        Attendee foundFirstname = attendeeRepo.findByFirstName(firstName);
        Attendee foundLastname =attendeeRepo.findByLastName(lastname);
        return foundFirstname == null || foundLastname == null;

    }
}
