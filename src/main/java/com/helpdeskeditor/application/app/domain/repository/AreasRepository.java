package com.helpdeskeditor.application.app.domain.repository;

import com.helpdeskeditor.application.app.domain.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreasRepository extends JpaRepository<Area, Integer> {

    @Override
    List<Area> findAll();

}
