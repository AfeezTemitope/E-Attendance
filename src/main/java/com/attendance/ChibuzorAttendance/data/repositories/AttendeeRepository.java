package com.attendance.ChibuzorAttendance.data.repositories;

import com.attendance.ChibuzorAttendance.data.models.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendeeRepository  extends JpaRepository<Attendee, Long> {
    boolean existsBySecretIdIgnoreCase(String secretId);
    List<Attendee> findAllByDepartmentNameIgnoreCase(String departmentName);
}
