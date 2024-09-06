package com.app.blog.model.dto;

import com.app.blog.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private Role role;
}
