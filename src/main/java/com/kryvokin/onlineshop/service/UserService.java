package com.kryvokin.onlineshop.service;

import com.kryvokin.onlineshop.model.User;
import com.kryvokin.onlineshop.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public boolean existsWithSameEmail(String email){
        return userRepository.getUserByEmail(email) != null;
    }
}