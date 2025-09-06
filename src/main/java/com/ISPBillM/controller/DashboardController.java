package com.ISPBillM.controller;

import com.ISPBillM.repository.EmployeeRepository;
import com.ISPBillM.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {


        int totalServices = serviceRepository.findAll().size();
        int pendingPayments = 0;
        int activeServices = 0;
        int totalEmployees = employeeRepository.findAll().size();


        model.addAttribute("currentPage", "dashboard");
        model.addAttribute("totalServices", totalServices);
        model.addAttribute("pendingPayments", pendingPayments);
        model.addAttribute("activeServices", activeServices);
        model.addAttribute("totalEmployees", totalEmployees);

        return "dashboard";
    }
}
