package com.helpdeskeditor.application.app.data.repository;

import java.util.UUID;

import com.helpdeskeditor.application.app.data.entity.User;
import com.helpdeskeditor.application.app.data.entity.UsuarioSoporteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);

}