package com.helpdeskeditor.application.app.data.repository;

import com.helpdeskeditor.application.app.data.entity.IncidenciaEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidenciaRepository extends JpaRepository<IncidenciaEntity, Integer> {
    List<IncidenciaEntity> findAll(Sort sort);

}
