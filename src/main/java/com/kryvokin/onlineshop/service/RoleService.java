package com.kryvokin.onlineshop.service;

import com.kryvokin.onlineshop.model.Role;
import com.kryvokin.onlineshop.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Iterable<Role> getAll(){
        return roleRepository.findAll();
    }
}
