package com.helpdeskeditor.application.app.datos.repository;

import com.helpdeskeditor.application.app.datos.entity.UnidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadRepository extends JpaRepository<UnidadEntity, Integer> {

}
