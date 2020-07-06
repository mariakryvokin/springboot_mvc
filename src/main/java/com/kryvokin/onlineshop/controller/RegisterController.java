package com.kryvokin.onlineshop.controller;

import com.kryvokin.onlineshop.model.User;
import com.kryvokin.onlineshop.service.LocalResolverService;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;
import java.util.ResourceBundle;

@Controller
public class RegisterController {

    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private LocalResolverService localResolverService;

    public RegisterController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder,
                              LocalResolverService localResolverService) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.localResolverService = localResolverService;
    }

    @GetMapping("/register")
    public String getRegisterView(@RequestParam(name = "error",required = false) String error, Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("error", error);
        model.addAttribute("allRoles", roleService.getAll());
        return "register";
    }

    @PostMapping("/register")
    public RedirectView getRegisterView(@Valid @ModelAttribute("user") User user, RedirectAttributes redirectAttributes,
                                        HttpServletRequest httpServletRequest) {
        if (userService.existsWithSameEmail(user.getEmail())) {
            String errorMessage = getErrorMessage(user.getEmail(), httpServletRequest);
            return redirectToRegisterView(errorMessage,user, redirectAttributes);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return new RedirectView("login");
    }

    private String getErrorMessage(String email, HttpServletRequest httpServletRequest) {
        Locale locale = localResolverService.resolveLocal(httpServletRequest);
        ResourceBundle exampleBundle = ResourceBundle.getBundle("messages", locale);
        return String.format(exampleBundle.getString("message.register.error"), email);
    }

    private RedirectView redirectToRegisterView(String error, @ModelAttribute("user") @Valid User user,
                                                RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("error",error);
        return new RedirectView("register");
    }
}
