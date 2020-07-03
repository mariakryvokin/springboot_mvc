package com.kryvokin.onlineshop.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private String adminRole = "ADMIN";
    private String userRole = "USER";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        if (roles.contains(new SimpleGrantedAuthority(adminRole))) {
            httpServletResponse.sendRedirect("/admin/main");
        } else if (roles.contains(new SimpleGrantedAuthority(userRole))) {
            httpServletResponse.sendRedirect("/user/main");
        }
    }
}
