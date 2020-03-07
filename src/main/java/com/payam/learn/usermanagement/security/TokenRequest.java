package com.payam.learn.usermanagement.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class TokenRequest {

    private String email;
    private String password;

}
