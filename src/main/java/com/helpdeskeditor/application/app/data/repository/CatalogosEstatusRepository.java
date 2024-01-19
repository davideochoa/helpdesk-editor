package com.helpdeskeditor.application.app.data.repository;

import com.helpdeskeditor.application.app.data.entity.CatalogoEstatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogosEstatusRepository extends JpaRepository<CatalogoEstatusEntity, Integer> {

}
