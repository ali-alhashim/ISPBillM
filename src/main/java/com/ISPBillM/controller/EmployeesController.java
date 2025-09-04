package com.ISPBillM.controller;

import com.ISPBillM.dto.EmployeeDto;
import com.ISPBillM.dto.ServiceDto;
import com.ISPBillM.entity.EmployeeEntity;
import com.ISPBillM.entity.ServiceEntity;
import com.ISPBillM.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EmployeesController {


    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public String employees(Model model) {
        model.addAttribute("currentPage", "employees");
        // Add other model attributes as needed
        EmployeeDto employeeDto= new EmployeeDto();
        List<EmployeeEntity> employees = employeeRepository.findAll();

        model.addAttribute("employee", employeeDto);
        model.addAttribute("employees", employees);
        return "employees";
    }
}
