package com.ISPBillM.controller;


import com.ISPBillM.dto.PaymentDto;
import com.ISPBillM.entity.PaymentEntity;
import com.ISPBillM.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;


@Controller
public class BillController {

    @Autowired
    PaymentRepository paymentRepository;

    @GetMapping("/bills")
    public String bills(Model model)
    {

        BigDecimal totalBilled = BigDecimal.valueOf(0.00);
        BigDecimal totalPaid   = BigDecimal.valueOf(0.00);
        BigDecimal totalPending = BigDecimal.valueOf(0.00);
        BigDecimal totalOverdue = BigDecimal.valueOf(0.00);

        List<PaymentEntity> payments = paymentRepository.findAll();

        PaymentDto payment = new PaymentDto();

        model.addAttribute("payments", payments);
        model.addAttribute("payment", payment);

        return "bills";
    }
}
