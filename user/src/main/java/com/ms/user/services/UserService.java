package com.ms.user.services;

import org.springframework.stereotype.Service;

import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserRepository;

import jakarta.transaction.Transactional;

// Bean
@Service
public class UserService {

    final UserRepository userRepository;
    final UserProducer userProducer;

    // Injeção de dependência
    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @Transactional
    public UserModel save(UserModel userModel) {
        userModel = this.userRepository.save(userModel);
        userProducer.publishMessageEmail(userModel);
        return userModel;
    }
}
