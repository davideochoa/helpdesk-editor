package com.helpdeskeditor.application.app.data.repository;

import com.helpdeskeditor.application.app.data.entity.AreaEntity;
import com.helpdeskeditor.application.app.data.entity.SolicitudEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<SolicitudEntity, Integer> {

}
