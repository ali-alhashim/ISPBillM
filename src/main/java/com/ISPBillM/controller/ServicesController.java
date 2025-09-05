package com.ISPBillM.controller;

import com.ISPBillM.dto.ServiceDto;
import com.ISPBillM.entity.BranchEntity;
import com.ISPBillM.entity.DepartmentEntity;
import com.ISPBillM.entity.EmployeeEntity;
import com.ISPBillM.entity.ServiceEntity;
import com.ISPBillM.repository.BranchRepository;
import com.ISPBillM.repository.DepartmentRepository;
import com.ISPBillM.repository.EmployeeRepository;
import com.ISPBillM.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ServicesController {

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    BranchRepository branchRepository;

    @GetMapping("/services")
    public String dashboard(Model model) {
        model.addAttribute("currentPage", "services");
        // Add other model attributes as needed
        ServiceDto serviceDto = new ServiceDto();
        List<ServiceEntity> services = serviceRepository.findAll();
        List<EmployeeEntity> employees = employeeRepository.findAll();
        List<DepartmentEntity> departments = departmentRepository.findAll();
        List<BranchEntity> branches = branchRepository.findAll();

        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);
        model.addAttribute("branches", branches);
        model.addAttribute("service", serviceDto);
        model.addAttribute("services", services);
        return "services";
    }


}
