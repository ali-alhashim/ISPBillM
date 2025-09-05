package com.ISPBillM.controller;


import com.ISPBillM.dto.DepartmentDto;
import com.ISPBillM.entity.DepartmentEntity;
import com.ISPBillM.entity.EmployeeEntity;
import com.ISPBillM.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping("/departments")
    public String departments(Model model)
    {
        List<DepartmentEntity> departments = departmentRepository.findAll();
        DepartmentDto department = new DepartmentDto();

        //get the current user
        EmployeeEntity currentUser =(EmployeeEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(currentUser ==null)
        {
            return "redirect:/login";
        }



        model.addAttribute("currentUser", currentUser);
        model.addAttribute("departments",departments);
        model.addAttribute("department",department);

        return "departments";
    }

    @PostMapping("/departments/save")
    public String addDepartment(@ModelAttribute DepartmentDto departmentDto)
    {
        System.out.println("Add New Department");
        DepartmentEntity newDepartment = new DepartmentEntity();
        newDepartment.setName(departmentDto.getName());
        newDepartment.setAddress(departmentDto.getAddress());
        newDepartment.setDescription(departmentDto.getDescription());
        newDepartment.setStatus(departmentDto.getStatus());

        departmentRepository.save(newDepartment);
        return "redirect:/departments";
    }
}
