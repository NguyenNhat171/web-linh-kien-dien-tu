package com.example.electronicshop.communication.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
public class ChangePassword {
    @NotBlank(message = "New password is set")
    public String newpass;
    @NotBlank(message = "Old password have been change")
    public String oldpasss;
}
