package com.vevohub.integrator.database.service;

import com.vevohub.integrator.database.entity.UserEntitydoi;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServicedoi {


    private static final String EXISTING_EMAIL = "test@test.com";

    public Optional<UserEntitydoi> findByEmail(String email) {
        //TODO: Move this to a database


        if (!EXISTING_EMAIL.equalsIgnoreCase(email)) return Optional.empty();
        var user = new UserEntitydoi();
        user.setId(1L);
        user.setEmail(EXISTING_EMAIL);
        user.setPassword("$2a$12$4j6Y2ZUULBqWfqDMD35cbOvU7AqLOJqo1c3l5rosNazYZrFmFezeG"); // = test
        user.setRole("ROLE_ADMIN");
        user.setExtraInfo("My nice Admin");
        return Optional.of(user);
     }

}
