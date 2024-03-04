package com.vevohub.integrator.database.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserEmailUserNameDto {

    // Setters
    // Getters
    private String email;
    private String username;

    public UserEmailUserNameDto(String email, String username) {
        this.email = email;
        this.username = username;
    }
}
