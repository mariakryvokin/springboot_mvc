package com.kryvokin.onlineshop.controller;

import com.kryvokin.onlineshop.model.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/main")
    public String doHome(Model model){
        model.addAttribute("local", Local.values());
        return "main";
    }
}
