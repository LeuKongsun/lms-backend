package com.kongsun.leanring.system.auth;

import com.kongsun.leanring.system.user.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotNull(message = "firstname is required")
    private String firstname;
    @NotNull(message = "lastname is required")
    private String lastname;
    @NotNull(message = "username is required")
    private String username;
    @NotNull(message = "password is required")
    private String password;
    @NotNull(message = "role is required")
    private Role role;
}
