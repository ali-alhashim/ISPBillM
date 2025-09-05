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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    @PostMapping("/services/save")
    public String addService(@ModelAttribute("service") ServiceDto serviceDto, RedirectAttributes redirectAttributes)
    {
        ServiceEntity newService = new ServiceEntity();
        newService.setAccountNumber(serviceDto.getAccountNumber());
        newService.setActivationDate(serviceDto.getActivationDate());
        newService.setMonthlyFee(serviceDto.getMonthlyFee());
        newService.setNotes(serviceDto.getNotes());
        newService.setProvider(serviceDto.getProvider());
        newService.setStatus(serviceDto.getStatus());
        newService.setSerialNumber(serviceDto.getSerialNumber());
        newService.setType(serviceDto.getType());

        String assignedToId = serviceDto.getAssignedToId();

        // Check if the string starts with "employee--"
        if (assignedToId.startsWith("employee--")) {
            // If it does, extract the part after "employee--"
            String employeeId = assignedToId.substring("employee--".length());
            // Now you have the employeeId and can use it to find the employee
            EmployeeEntity employee = employeeRepository.findById(employeeId).orElse(null);
            if(employee ==null)
            {
                System.out.println("Employee is null !!");
            }
            newService.setEmployee(employee);
            System.out.println("Extracted employee ID: " + employeeId);
            serviceRepository.save(newService);

            List<ServiceEntity> employeeServices = new ArrayList<>();
            if(employee.getServices() != null)
            {
                employeeServices = employee.getServices();
            }

            employeeServices.add(newService);
            employee.setServices(employeeServices);
            employeeRepository.save(employee);

        } else if (assignedToId.startsWith("department--")) {
            // Handle the department case
            String departmentId = assignedToId.substring("department--".length());
            DepartmentEntity department = departmentRepository.findById(departmentId).orElse(null);
            newService.setDepartment(department);
            System.out.println("Extracted department ID: " + departmentId);
            serviceRepository.save(newService);

        } else if (assignedToId.startsWith("branch--")) {
            // Handle the branch case
            String branchId = assignedToId.substring("branch--".length());
            BranchEntity branch = branchRepository.findById(branchId).orElse(null);
            newService.setBranch(branch);
            System.out.println("Extracted branch ID: " + branchId);
            serviceRepository.save(newService);

        } else {
            // Handle cases where the ID doesn't match any of the prefixes
            System.out.println("The assigned ID doesn't have a valid prefix.");
        }


        redirectAttributes.addFlashAttribute("message", "Service saved successfully!");
        return "redirect:/services";
    }


}
