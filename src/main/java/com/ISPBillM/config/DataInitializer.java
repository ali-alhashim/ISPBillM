package com.ISPBillM.config;

import com.ISPBillM.entity.EmployeeEntity;
import com.ISPBillM.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void seedAdmin() {
        if (employeeRepository.findByUsername("admin").isEmpty()) {
            EmployeeEntity admin = new EmployeeEntity();
            admin.setName("admin");
            admin.setUsername("admin");
            admin.setStatus("Active");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole("ADMIN");
            employeeRepository.save(admin);
            System.out.println("✅ Default admin user created: admin/admin");
        }
    }
}