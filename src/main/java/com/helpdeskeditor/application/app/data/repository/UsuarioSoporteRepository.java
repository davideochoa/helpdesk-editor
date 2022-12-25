package com.helpdeskeditor.application.app.data.repository;

import com.helpdeskeditor.application.app.data.entity.UsuarioSoporteEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioSoporteRepository extends JpaRepository<UsuarioSoporteEntity, Integer> {
    List<UsuarioSoporteEntity> findAll(Sort sort);

}
