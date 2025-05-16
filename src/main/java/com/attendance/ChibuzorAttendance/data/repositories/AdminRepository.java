package com.attendance.ChibuzorAttendance.data.repositories;

import com.attendance.ChibuzorAttendance.data.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    boolean existsByUsername(String username);
    Optional<Admin> findByUsername(String userName);
}
