package com.attendance.ChibuzorAttendance.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String departmentName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Admin admin;
    
}
