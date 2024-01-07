package com.vevohub.integrator.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserEntitydoi {


    private Long id;

    private String username;
    private String email;

    @JsonIgnore
    private String password;

    private String role;
    private String extraInfo;

}
