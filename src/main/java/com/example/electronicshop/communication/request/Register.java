package com.example.electronicshop.communication.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
@AllArgsConstructor
public class Register {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Email is required")
    @Size(max = 50)
    @Email(message = "Email invalidate")
    private String email;
    @NotBlank(message = "Password can not be null")
    @Size( min = 5, max = 50)
    private String password;
    @NotBlank(message = "Phone can not be null")
    private String role;
}
