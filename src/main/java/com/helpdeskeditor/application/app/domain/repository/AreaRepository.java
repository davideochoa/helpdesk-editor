package com.helpdeskeditor.application.app.domain.repository;

import com.helpdeskeditor.application.app.domain.entity.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<AreaEntity, Integer> {

    @Override
    List<AreaEntity> findAll();

}
