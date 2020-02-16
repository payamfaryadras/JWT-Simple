package com.payam.learn.usermanagement.models;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
@ToString(exclude = "password")
@Data
@Accessors(chain = true)
@Entity
public class User {
    @Column(unique = true)
    @NotBlank(message = "{user.email.blank}")
    private String email;
    @NotBlank(message = "{user.password.blank}")
    private String password;
    @Id
    private UUID id;
    public User(){
        this.id = UUID.randomUUID();
    }

}
