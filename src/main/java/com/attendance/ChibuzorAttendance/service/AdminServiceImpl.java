package com.attendance.ChibuzorAttendance.service;

import com.attendance.ChibuzorAttendance.Exception.GenericException;
import com.attendance.ChibuzorAttendance.Exception.UsernameAlreadyTakenException;
import com.attendance.ChibuzorAttendance.Exception.WrongPasswordException;
import com.attendance.ChibuzorAttendance.data.models.Admin;
import com.attendance.ChibuzorAttendance.data.repositories.AdminRepository;
import com.attendance.ChibuzorAttendance.dto.request.LoginRequest;
import com.attendance.ChibuzorAttendance.dto.request.SignUpRequest;
import com.attendance.ChibuzorAttendance.dto.response.LoginResponse;
import com.attendance.ChibuzorAttendance.dto.response.SignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) throws GenericException {
        // Fetch user from DB
        Optional<Admin> userOpt = adminRepository.findByUsername(request.getUsername());

        if (userOpt.isPresent()) {
            Admin user = userOpt.get();

            // Validate password manually (you can use BCrypt for real apps)
            if (user.getPassword().equals(request.getPassword())) {
                String token = jwtUtil.generateToken(user.getUsername());
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setToken(token);
                loginResponse.setMessage("Login successful");
                return loginResponse;
            } else {
                throw new WrongPasswordException("Invalid password");
            }
        } else {
            throw new GenericException("User not found");
        }
    }

    @Override
    public SignUpResponse signUp(SignUpRequest request) {
        if(adminRepository.existsByUsername(request.getUserName())){
            throw new UsernameAlreadyTakenException("UserName Has Already Been Taken ");
        }
        Admin admin = new Admin();
        admin.setUsername(request.getUserName());
        admin.setPassword(request.getPassword());
        admin.setEmail(request.getEmail());
        adminRepository.save(admin);

        SignUpResponse response = new SignUpResponse();
        response.setMessage("Successfully Signed Up");
        return response;
    }
}
