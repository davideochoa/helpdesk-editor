package com.helpdeskeditor.application.app.domain.repository;

import com.helpdeskeditor.application.app.domain.entity.Unidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnidadesRepository extends JpaRepository<Unidad, Integer> {

    @Override
    List<Unidad> findAll();

}
