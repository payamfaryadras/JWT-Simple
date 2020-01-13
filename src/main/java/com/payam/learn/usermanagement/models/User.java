package com.payam.learn.usermanagement.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@ToString(exclude = "password")
@Data
@Accessors(chain = true)
public class User {

    private String email;
    private String password;
    private UUID id;
    public User(){
        this.id = UUID.randomUUID();
    }

}
