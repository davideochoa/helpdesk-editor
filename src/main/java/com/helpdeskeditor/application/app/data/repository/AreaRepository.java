package com.helpdeskeditor.application.app.data.repository;

import com.helpdeskeditor.application.app.data.entity.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<AreaEntity, Integer> {

    List<AreaEntity> findByidUnidad(int idUnidad);
}
