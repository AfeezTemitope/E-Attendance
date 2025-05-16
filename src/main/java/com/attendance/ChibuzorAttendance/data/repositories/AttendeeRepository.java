package com.attendance.ChibuzorAttendance.data.repositories;

import com.attendance.ChibuzorAttendance.data.models.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendeeRepository  extends JpaRepository<Attendee, Long> {
    boolean existsBySecretIdIgnoreCase(String secretId);


    Optional<List<Attendee>> findAllByDepartmentNameIgnoreCase(String departmentName);
    Attendee findByFirstName(String firstName);
    Attendee findByLastName(String lastName);
}
