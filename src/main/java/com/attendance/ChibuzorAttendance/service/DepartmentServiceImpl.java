package com.attendance.ChibuzorAttendance.service;


import com.attendance.ChibuzorAttendance.Exception.DepartmentNotFoundException;
import com.attendance.ChibuzorAttendance.Exception.DepartmentAlreadyExistException;
import com.attendance.ChibuzorAttendance.Exception.WrongPasswordException;
import com.attendance.ChibuzorAttendance.data.models.Department;
import com.attendance.ChibuzorAttendance.data.repositories.DepartmentRepository;
import com.attendance.ChibuzorAttendance.dto.request.LoginDepartmentRequest;
import com.attendance.ChibuzorAttendance.dto.response.CreateDepartmentResponse;
import com.attendance.ChibuzorAttendance.dto.request.CreateDepartmentRequest;
import com.attendance.ChibuzorAttendance.dto.response.GetDepartmentResponse;
import com.attendance.ChibuzorAttendance.dto.response.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

//    private final ModelMapper modelMapper;

    private final DepartmentRepository departmentRepository;

    @Override
    public CreateDepartmentResponse createDepartment(CreateDepartmentRequest createAttendanceDepartment) throws DepartmentAlreadyExistException {

        if(departmentRepository.existsByDepartmentName(createAttendanceDepartment.getDepartmentName())){
            throw new DepartmentAlreadyExistException("Department Already Exist");
        }
        Department newUser = new Department();
        newUser.setDepartmentName(createAttendanceDepartment.getDepartmentName());
        newUser.setPassword(createAttendanceDepartment.getPassword());
        newUser = departmentRepository.save(newUser);
        CreateDepartmentResponse response = new CreateDepartmentResponse();
        response.setDepartmentName(newUser.getDepartmentName());
        response.setId(newUser.getId());
        response.setMessage("done");
        return response;
    }

    @Override
    public GetDepartmentResponse getDepartment(String departmentName) throws DepartmentNotFoundException {
        Department department = departmentRepository.findByDepartmentName(departmentName)
                .orElseThrow(() -> new DepartmentNotFoundException("department not found"));

        GetDepartmentResponse response = new GetDepartmentResponse();
        response.setDepartment(department);
        response.setMessage("Found");
        return response;
    }

    @Override
    public LoginResponse loginDepartment(LoginDepartmentRequest request) throws DepartmentNotFoundException, WrongPasswordException {
        GetDepartmentResponse foundDepartment = getDepartment(request.getDepartment());
        if(foundDepartment.getDepartment().getPassword().equalsIgnoreCase(request.getPassword())){
            LoginResponse response = new LoginResponse();
            response.setMessage("Login successful");
            return response;
        }
        throw new WrongPasswordException("wrong password");
    }

}
