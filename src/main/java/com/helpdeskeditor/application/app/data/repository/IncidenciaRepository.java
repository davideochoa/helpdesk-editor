package com.helpdeskeditor.application.app.data.repository;

import com.helpdeskeditor.application.app.data.entity.IncidenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenciaRepository extends JpaRepository<IncidenciaEntity, Integer> {

    //@Override
    //List<IncidenciaEntity> findAll();

}
