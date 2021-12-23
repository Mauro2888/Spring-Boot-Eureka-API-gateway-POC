package com.user.service.userservice.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateUserRequestModel {
    @NotNull(message = "Value firstName cannot be null")
    @Size(min = 2, message = "FirstName must not be less than two characters")
    public String firstName;

    @NotNull(message = "Value lastName cannot be null")
    @Size(min = 2, message = "LastName must not be less than two characters")
    public String lastName;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8,max = 16, message = "Password must be equal or grater than 8 and less than 16")
    public String password;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Value email must be in proper format")
    public String email;
}
