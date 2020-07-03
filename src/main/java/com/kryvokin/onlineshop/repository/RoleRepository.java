package com.kryvokin.onlineshop.repository;

import com.kryvokin.onlineshop.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {

}
