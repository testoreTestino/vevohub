package com.vevohub.integrator.service;

import com.vevohub.integrator.database.dao.UserRepository;
import com.vevohub.integrator.database.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Optional<UserEntity> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }
}
