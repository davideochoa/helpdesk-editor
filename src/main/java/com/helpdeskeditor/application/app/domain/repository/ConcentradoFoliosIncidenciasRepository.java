package com.helpdeskeditor.application.app.domain.repository;

import com.helpdeskeditor.application.app.domain.entity.ConcentradoFolioIncidencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConcentradoFoliosIncidenciasRepository extends JpaRepository<ConcentradoFolioIncidencia, Integer> {
    @Override
    List<ConcentradoFolioIncidencia> findAll();

    List<ConcentradoFolioIncidencia> findByUsuarioReporta();

}
