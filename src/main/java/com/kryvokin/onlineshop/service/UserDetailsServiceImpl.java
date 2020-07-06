package com.kryvokin.onlineshop.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.kryvokin.onlineshop.model.User user = userService.getUserByEmail(email);
        if (user != null) {
            List<SimpleGrantedAuthority> authorities = user.getRoles()
                    .stream().map(r->new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
            UserDetails userDetails = new User(user.getEmail(), user.getPassword(), authorities);
            return userDetails;
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }
}
