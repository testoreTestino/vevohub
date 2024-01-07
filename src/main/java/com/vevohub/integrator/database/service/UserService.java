package com.vevohub.integrator.database.service;

import com.vevohub.integrator.database.dao.UserRepository;
import com.vevohub.integrator.database.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public UserEntity registerUser(UserEntity userEntity) {
        userEntity.setPasswordHash(bCryptPasswordEncoder.encode(userEntity.getPasswordHash()));
        return userRepository.save(userEntity);
    }

}
