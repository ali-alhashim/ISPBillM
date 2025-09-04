package com.ISPBillM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/my-login")
    public String login()
    {
        return "login";
    }
}
