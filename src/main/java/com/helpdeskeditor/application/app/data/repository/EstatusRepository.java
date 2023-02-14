package com.helpdeskeditor.application.app.data.repository;

import com.helpdeskeditor.application.app.data.DAO.EstatusDAO;
import com.helpdeskeditor.application.app.data.entity.EstatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstatusRepository extends CrudRepository<EstatusEntity, Integer> {
    public List<EstatusEntity> findByFolioOrderByFecha(Integer Folio);

    @Query("SELECT new com.helpdeskeditor.application.app.data.DAO.EstatusDAO( " +
            "e.id, " +
            "e.folio, " +
            "e.idEstatus, " +
            "ce.nombre," +
            "e.anotacion, " +
            "e.idUsuario, " +
            "cs.nombrePropio," +
            "e.fecha) " +
            "FROM EstatusEntity e,CatalogoEstatusEntity ce, UsuarioSoporteEntity cs " +
            "WHERE e.folio = :folio " +
            "AND ce.id = e.idEstatus " +
            "AND cs.id = e.idUsuario " +
            "ORDER BY e.fecha")
    public List<EstatusDAO> findAllDAO(Integer folio);
}
