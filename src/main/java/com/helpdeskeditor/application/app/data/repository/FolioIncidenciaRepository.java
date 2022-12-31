package com.helpdeskeditor.application.app.data.repository;

import com.helpdeskeditor.application.app.data.entity.FolioIncidenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FolioIncidenciaRepository extends CrudRepository<FolioIncidenciaEntity, Integer> {

    @Query("SELECT CFI.usuarioReporta "+
            "FROM FolioIncidenciaEntity CFI "+
            "GROUP BY CFI.usuarioReporta ORDER BY CFI.usuarioReporta ASC")
    List<String> getAllUsuarioReporta();

    @Query("SELECT CFI.marca "+
            "FROM FolioIncidenciaEntity CFI "+
            "GROUP BY CFI.marca ORDER BY CFI.marca ASC")
    List<String> getAllMarca();

    @Query("SELECT CFI.modelo "+
            "FROM FolioIncidenciaEntity CFI "+
            "GROUP BY CFI.modelo ORDER BY CFI.modelo ASC")
    List<String> getAllModelo();

    @Query("SELECT CFI.modelo "+
            "FROM FolioIncidenciaEntity CFI "+
            "WHERE CFI.marca = :marca "+
            "GROUP BY CFI.modelo ORDER BY CFI.modelo ASC")
    List<String> findModeloByMarca(String marca);

}
