package com.attendance.ChibuzorAttendance.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Admin  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(unique=true)
    @NotBlank
    private String username;
    @NonNull
    @NotBlank
    private String password;
    @NonNull
    @NotBlank
    @Column(unique=true)
    private String  email;

    @OneToMany(fetch = FetchType.EAGER)
    List<Department> departments;
}
