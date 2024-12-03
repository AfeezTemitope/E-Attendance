package com.attendance.ChibuzorAttendance.data.repositories;

import com.attendance.ChibuzorAttendance.data.models.AttendanceUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttendanceUserRepository extends JpaRepository<AttendanceUser, Long> {
    boolean existsByUsername(String username);

    Optional <AttendanceUser> findByUsername(String username);
}
