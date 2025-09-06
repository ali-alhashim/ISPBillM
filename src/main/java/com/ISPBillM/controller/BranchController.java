package com.ISPBillM.controller;

import com.ISPBillM.dto.BranchDto;

import com.ISPBillM.entity.BranchEntity;

import com.ISPBillM.entity.EmployeeEntity;
import com.ISPBillM.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BranchController {

    @Autowired
    BranchRepository branchRepository;

    @GetMapping("/branches")
    public String branches(Model model)
    {
        List<BranchEntity> branches = branchRepository.findAll();
        BranchDto branch = new BranchDto();

        //get the current user
        EmployeeEntity currentUser =(EmployeeEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(currentUser ==null)
        {
            return "redirect:/login";
        }



        model.addAttribute("currentUser", currentUser);
        model.addAttribute("branches",branches);
        model.addAttribute("branch",branch);

        return "branches";
    }

    @PostMapping("/branches/save")
    public String addBranch(@ModelAttribute BranchDto branchDto)
    {
        System.out.println("Add new Branch");

        BranchEntity newBranch = new BranchEntity();
        newBranch.setName(branchDto.getName());
        newBranch.setAddress(branchDto.getAddress());
        newBranch.setDescription(branchDto.getDescription());
        newBranch.setStatus(branchDto.getStatus());
        branchRepository.save(newBranch);
        return "redirect:/branches";
    }
}
