package com.helpdeskeditor.application.app.data.repository;

import com.helpdeskeditor.application.app.data.entity.UnidadEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadRepository extends JpaRepository<UnidadEntity, Integer> {
    public List<UnidadEntity> findAll(Sort sort);
}
