package com.helpdeskeditor.application.app.domain.repository;

import com.helpdeskeditor.application.app.domain.entity.IncidenciaEntity;
import com.helpdeskeditor.application.app.domain.entity.UnidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidenciaRepository extends JpaRepository<IncidenciaEntity, Integer> {

    //@Override
    //List<IncidenciaEntity> findAll();

}
