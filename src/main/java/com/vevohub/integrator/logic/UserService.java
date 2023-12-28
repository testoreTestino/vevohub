package com.vevohub.integrator.logic;

import com.vevohub.integrator.dao.UserRepository;
import com.vevohub.integrator.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public User registerUser(User user) {
        user.setPasswordHash(bCryptPasswordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(user);
    }

}
