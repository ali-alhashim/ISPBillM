package com.ISPBillM.controller;

import com.ISPBillM.dto.EmployeeDto;
import com.ISPBillM.dto.ServiceDto;
import com.ISPBillM.entity.EmployeeEntity;
import com.ISPBillM.entity.ServiceEntity;
import com.ISPBillM.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        //get the current user
        EmployeeEntity currentUser =(EmployeeEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(currentUser ==null)
        {
            return "redirect:/login";
        }





        model.addAttribute("currentUser", currentUser);
        model.addAttribute("employee", employeeDto);
        model.addAttribute("employees", employees);
        return "employees";
    }

    @PostMapping("/employees/save")
    public String addNewEmployee(@ModelAttribute("employee") EmployeeDto employeeDto,
                                 @RequestParam(value = "avatar", required = false) MultipartFile file,
                                 RedirectAttributes redirectAttributes) {

        System.out.println("Add New Employee");

        // You can now access form data from employeeDto
        System.out.println("Name: " + employeeDto.getName());
        System.out.println("Email: " + employeeDto.getEmail());

        // You can now access the uploaded file
        if (!file.isEmpty()) {
            System.out.println("File received: " + file.getOriginalFilename());
            // Add logic to save the file
        }


        EmployeeEntity newEmployee = new EmployeeEntity();
        newEmployee.setName(employeeDto.getName());
        newEmployee.setEmail(employeeDto.getEmail());
        newEmployee.setRole(employeeDto.getRole());
        newEmployee.setNote(employeeDto.getNote());
        newEmployee.setBadgeNumber(employeeDto.getBadgeNumber());
        newEmployee.setStatus(employeeDto.getStatus());

        // Set Department

        // set Branch

        //upload avatar if not empty and set the path

        employeeRepository.save(newEmployee);


        // Add a success flash message for a better user experience
        redirectAttributes.addFlashAttribute("message", "Employee saved successfully!");

        return "redirect:/employees";
    }
}
