package com.helpdeskeditor.application.app.data.repository;

import com.helpdeskeditor.application.app.data.entity.UsuarioSoporteEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuariosSoporteRepository extends CrudRepository<UsuarioSoporteEntity, Integer> {
    List<UsuarioSoporteEntity> findAll(Sort sort);

    List<UsuarioSoporteEntity> findAll();

    UsuarioSoporteEntity findByNombreUsuario(String nombreUsuario);

    Optional<UsuarioSoporteEntity> findById(Integer id);

}
