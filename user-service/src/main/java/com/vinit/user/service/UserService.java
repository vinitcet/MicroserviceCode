package com.vinit.user.service;

import com.vinit.user.entity.Users;
import com.vinit.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Users saveUser(Users user) {
        log.info("Saving user :service");
        return userRepository.save(user);
    }

    public Users getUser(Long userId) {
        log.info("Getting user :service");
        return userRepository.findById(userId).get();
    }
}
