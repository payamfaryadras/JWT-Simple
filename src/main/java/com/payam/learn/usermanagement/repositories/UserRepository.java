package com.payam.learn.usermanagement.repositories;

import com.payam.learn.usermanagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
