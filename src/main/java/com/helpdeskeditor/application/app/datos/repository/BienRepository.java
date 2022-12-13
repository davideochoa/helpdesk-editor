package com.helpdeskeditor.application.app.datos.repository;

import com.helpdeskeditor.application.app.datos.entity.AreaEntity;
import com.helpdeskeditor.application.app.datos.entity.BiendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BienRepository extends JpaRepository<BiendEntity, Integer> {
    List<BiendEntity> findByidTipoIncidencia(int idTipoIncidencia);
}
