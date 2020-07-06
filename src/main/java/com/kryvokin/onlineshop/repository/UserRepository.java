package com.kryvokin.onlineshop.repository;

import com.kryvokin.onlineshop.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    User getUserByEmail(String email);

}
