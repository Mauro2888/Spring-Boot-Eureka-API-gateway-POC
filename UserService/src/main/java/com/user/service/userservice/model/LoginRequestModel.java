package com.user.service.userservice.model;

import lombok.Data;

@Data
public class LoginRequestModel {
    private String email;
    private String password;
}
