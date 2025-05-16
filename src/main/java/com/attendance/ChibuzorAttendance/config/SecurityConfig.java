//package com.attendance.ChibuzorAttendance.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf(AbstractHttpConfigurer::disable)  // ✅ Modern way to disable CSRF
//            .authorizeHttpRequests(auth -> auth
//                    .requestMatchers("/api/v1/auth/signup", "/api/v1/auth/login", "/public/**").permitAll() // ✅ open routes
//                    .anyRequest().authenticated()
//            )
//            .httpBasic(Customizer.withDefaults()) // ✅ HTTP Basic Auth
//            .formLogin(Customizer.withDefaults()); // ✅ Form-based login (optional)
//
//        return http.build();
//    }
//
//
//
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        return new InMemoryUserDetailsManager(
//                User.builder()
//                        .username("john")
//                        .password(passwordEncoder.encode("mypassword"))
//                        .roles("USER")
//                        .build(),
//                User.builder()
//                        .username("admin")
//                        .password(passwordEncoder.encode("admin123"))
//                        .roles("ADMIN")
//                        .build()
//        );
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//}
