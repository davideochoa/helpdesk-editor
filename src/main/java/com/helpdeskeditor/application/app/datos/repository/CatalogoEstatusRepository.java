package com.helpdeskeditor.application.app.datos.repository;

import com.helpdeskeditor.application.app.datos.entity.CatalogoEstatusEntity;
import com.helpdeskeditor.application.app.datos.entity.UnidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogoEstatusRepository extends JpaRepository<CatalogoEstatusEntity, Integer> {

}
