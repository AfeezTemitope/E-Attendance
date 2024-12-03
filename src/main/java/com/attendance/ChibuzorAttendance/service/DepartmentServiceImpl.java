package com.attendance.ChibuzorAttendance.service;


import com.attendance.ChibuzorAttendance.Exception.DepartmentNotFoundException;
import com.attendance.ChibuzorAttendance.Exception.UserAlreadyExistException;
import com.attendance.ChibuzorAttendance.data.models.Department;
import com.attendance.ChibuzorAttendance.data.repositories.DepartmentRepository;
import com.attendance.ChibuzorAttendance.dto.response.CreateDepartmentResponse;
import com.attendance.ChibuzorAttendance.dto.request.CreateDepartmentRequest;
import com.attendance.ChibuzorAttendance.dto.response.GetDepartmentResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

//    private final ModelMapper modelMapper;

    private final DepartmentRepository departmentRepository;

    @Override
    public CreateDepartmentResponse createDepartment(CreateDepartmentRequest createAttendanceUser) throws UserAlreadyExistException {

        if(departmentRepository.existsByDepartmentName(createAttendanceUser.getDepartmentName())){
            throw new UserAlreadyExistException("User Already Exist");
        }
        Department newUser = new Department();
        newUser.setDepartmentName(createAttendanceUser.getDepartmentName());
        newUser.setPassword(createAttendanceUser.getPassword());
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

}
