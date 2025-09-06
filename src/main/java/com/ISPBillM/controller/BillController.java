package com.ISPBillM.controller;


import com.ISPBillM.dto.PaymentDto;
import com.ISPBillM.entity.BillEntity;
import com.ISPBillM.entity.PaymentEntity;
import com.ISPBillM.entity.ServiceEntity;
import com.ISPBillM.repository.BillRepository;
import com.ISPBillM.repository.PaymentRepository;
import com.ISPBillM.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// Bill & payment Controller

@Controller
public class BillController {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    BillRepository billRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @GetMapping("/bills")
    public String bills(Model model)
    {

        BigDecimal totalBilled = BigDecimal.valueOf(0.00);
        BigDecimal totalPaid   = BigDecimal.valueOf(0.00);
        BigDecimal totalPending = BigDecimal.valueOf(0.00);
        BigDecimal totalOverdue = BigDecimal.valueOf(0.00);

        List<PaymentEntity> payments = paymentRepository.findAll();

        List<ServiceEntity> services = serviceRepository.findAll();

        PaymentDto payment = new PaymentDto();

        model.addAttribute("payments", payments);
        model.addAttribute("payment", payment);
        model.addAttribute("services", services);
        model.addAttribute("currentPage", "bills");

        return "bills";
    }

    @PostMapping("/payments/save")
    public String addBill_payments(@ModelAttribute PaymentDto paymentDto)
    {
        //Create Bill & Payment
        BillEntity newBill = new BillEntity();
        PaymentEntity newPayment = new PaymentEntity();

        String serviceId = paymentDto.getServiceId();
        ServiceEntity theService = serviceRepository.findById(serviceId).orElse(null);
        if(theService !=null)
        {
            newBill.setService(theService);
            newBill.setDueAmount(paymentDto.getAmount());
            billRepository.save(newBill);

            List<BillEntity> serviceBills = new ArrayList<>();
            if(theService.getBills() !=null)
            {
                serviceBills = theService.getBills();
                serviceBills.add(newBill);
            }
            else
            {
                serviceBills.add(newBill);
            }

            serviceRepository.save(theService);

        }


        return "redirect:/bills";
    }
}
