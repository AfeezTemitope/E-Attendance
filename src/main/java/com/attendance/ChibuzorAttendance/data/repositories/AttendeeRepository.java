package com.attendance.ChibuzorAttendance.data.repositories;

import com.attendance.ChibuzorAttendance.data.models.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeRepository  extends JpaRepository<Attendee, Long> {
    boolean existBySecretIdIgnoreCase(String secretId);
}
