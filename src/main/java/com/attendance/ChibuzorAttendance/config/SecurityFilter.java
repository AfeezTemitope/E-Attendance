//package com.attendance.ChibuzorAttendance.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityFilter {
//
//    @Bean
//    public SecurityFilter securityFilter(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrConfig -> csrConfig.disable())
//                .sessionManagement(sessionManagementConfig-> sessionManagementConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(authConfig -> {
//                    authConfig.requestMatchers()
//                })
//        return http.build();
//
//    }
//
//}
