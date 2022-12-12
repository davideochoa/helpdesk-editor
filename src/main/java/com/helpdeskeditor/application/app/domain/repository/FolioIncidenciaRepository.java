package com.helpdeskeditor.application.app.domain.repository;

import com.helpdeskeditor.application.app.domain.entity.FolioIncidenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FolioIncidenciaRepository extends JpaRepository<FolioIncidenciaEntity, Integer> {

    @Query("SELECT CFI.usuarioReporta "+
            "FROM FolioIncidenciaEntity CFI "+
            "GROUP BY CFI.usuarioReporta ORDER BY CFI.usuarioReporta ASC")
    List<String> getUsuarioReporta();

}
