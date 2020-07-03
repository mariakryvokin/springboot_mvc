package com.kryvokin.onlineshop.controller;

import com.kryvokin.onlineshop.model.User;
import com.kryvokin.onlineshop.service.RoleService;
import com.kryvokin.onlineshop.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    public RegisterController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String getRegisterView(@RequestParam(name = "error",required = false) String error, Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("error", error);
        model.addAttribute("allRoles", roleService.getAll());
        return "register";
    }

    @PostMapping("/register")
    public RedirectView getRegisterView(@Valid @ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        if (userService.existsWithSameEmail(user.getEmail())) {
            return redirectToRegisterView(user, redirectAttributes);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return new RedirectView("login");
    }

    private RedirectView redirectToRegisterView(@ModelAttribute("user") @Valid User user,
                                                RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("error",
                String.format("User with email %s already exists", user.getEmail()));
        return new RedirectView("register");
    }
}
