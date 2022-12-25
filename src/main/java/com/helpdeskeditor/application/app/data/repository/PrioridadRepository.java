package com.helpdeskeditor.application.app.data.repository;

import com.helpdeskeditor.application.app.data.entity.PrioridadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrioridadRepository extends JpaRepository<PrioridadEntity, Integer> {

}
