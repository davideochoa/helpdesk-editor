package com.helpdeskeditor.application.util;

import com.helpdeskeditor.application.util.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);
}