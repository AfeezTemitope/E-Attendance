package com.attendance.ChibuzorAttendance.data.repositories;

import com.attendance.ChibuzorAttendance.data.models.AttendanceSheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceSheetRepository extends JpaRepository<AttendanceSheet, Long> {
}
