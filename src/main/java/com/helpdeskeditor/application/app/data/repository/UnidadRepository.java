package com.helpdeskeditor.application.app.data.repository;

import com.helpdeskeditor.application.app.data.entity.UnidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadRepository extends JpaRepository<UnidadEntity, Integer> {

}
