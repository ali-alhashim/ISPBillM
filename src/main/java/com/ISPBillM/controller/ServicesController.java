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

        model.addAttribute("service", serviceDto);
        model.addAttribute("services", services);
        return "services";
    }

    @GetMapping("/api/search/assignable")
    public List<Map<String, Object>> searchAssignable(@RequestParam(value = "q", required = false) String query) {
        // Simulate data (replace this with DB/service logic)
        List<Map<String, Object>> allItems = new ArrayList<>();

        List<EmployeeEntity> employees = employeeRepository.findAll();
        List<DepartmentEntity> departments = departmentRepository.findAll();
        List<BranchEntity> branches = branchRepository.findAll();

        // Convert Employees
        for (EmployeeEntity emp : employeeRepository.findAll()) {
            allItems.add(Map.of(
                    "id", "emp-" + emp.getId(),
                    "text", emp.getName() + " (Employee)"
            ));
        }

        // Convert Departments
        for (DepartmentEntity dep : departmentRepository.findAll()) {
            allItems.add(Map.of(
                    "id", "dep-" + dep.getId(),
                    "text", dep.getName() + " (Department)"
            ));
        }

        // Convert Branches
        for (BranchEntity br : branchRepository.findAll()) {
            allItems.add(Map.of(
                    "id", "br-" + br.getId(),
                    "text", br.getName() + " (Branch)"
            ));
        }

        // Apply search filter if query provided
        if (query != null && !query.isBlank()) {
            String lowerQuery = query.toLowerCase();
            return allItems.stream()
                    .filter(item -> item.get("text").toString().toLowerCase().contains(lowerQuery))
                    .toList();
        }


        // Otherwise return all
        return allItems;
    }
}
