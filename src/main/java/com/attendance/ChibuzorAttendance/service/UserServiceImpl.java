package com.attendance.ChibuzorAttendance.service;


import com.attendance.ChibuzorAttendance.Exception.AttendanceUserException;
import com.attendance.ChibuzorAttendance.data.models.AttendanceUser;
import com.attendance.ChibuzorAttendance.data.repositories.AttendanceUserRepository;
import com.attendance.ChibuzorAttendance.dto.CreateAttendanceResponse;
import com.attendance.ChibuzorAttendance.dto.CreateAttendanceUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements AttendanceUserService {
    private final AttendanceUserRepository attendanceUserRepository;


    @Override
    public CreateAttendanceResponse createAUser(CreateAttendanceUser createAttendanceUser) throws AttendanceUserException {
        AttendanceUser user = attendanceUserRepository.findByUsername(createAttendanceUser.getUsername())
                        .orElseThrow(()-> new AttendanceUserException("User not found"));
        user.setUsername(createAttendanceUser.getUsername());
        user.setPassword(createAttendanceUser.getPassword());
        attendanceUserRepository.save(user);
        CreateAttendanceResponse response = new CreateAttendanceResponse();
        response.setMessage("done");
        return new CreateAttendanceResponse();
    }

}
