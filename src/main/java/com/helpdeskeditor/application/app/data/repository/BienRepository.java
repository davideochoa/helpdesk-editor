package com.helpdeskeditor.application.app.data.repository;

import com.helpdeskeditor.application.app.data.entity.BiendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BienRepository extends JpaRepository<BiendEntity, Integer> {

    List<BiendEntity> findByIdTipoIncidenciaOrderByNombreAsc(Integer IdTipoIncidencia);

    public BiendEntity findByIdTipoIncidenciaAndId(Integer IdTipoIncidencia, Integer Id);
}
