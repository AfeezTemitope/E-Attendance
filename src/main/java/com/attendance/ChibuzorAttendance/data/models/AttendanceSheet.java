package com.attendance.ChibuzorAttendance.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
public class AttendanceSheet {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    @JoinColumn()
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Attendee> attendees;
    private String departmentName;
}
