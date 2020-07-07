package com.kryvokin.onlineshop.controller;

import com.kryvokin.onlineshop.model.Local;
import com.kryvokin.onlineshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/main")
    public String doHome(Model model){
        model.addAttribute("local", Local.values());
        return "main";
    }

    @GetMapping("/user/main")
    public String doUserHome(Model model){
        return "user/main";
    }

    @GetMapping("/admin/main")
    public String doAdminHome(Model model){
        return "admin/main";
    }

}
