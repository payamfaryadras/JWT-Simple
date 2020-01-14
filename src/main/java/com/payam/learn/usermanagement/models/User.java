package com.payam.learn.usermanagement.models;

import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
@ToString(exclude = "password")
@Data
@Accessors(chain = true)
public class User {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private UUID id;
    public User(){
        this.id = UUID.randomUUID();
    }

}
