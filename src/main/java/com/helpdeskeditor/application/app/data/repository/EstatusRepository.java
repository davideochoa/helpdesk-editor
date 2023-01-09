package com.helpdeskeditor.application.app.data.repository;

import com.helpdeskeditor.application.app.data.DAO.EstatusDAO;
import com.helpdeskeditor.application.app.data.entity.EstatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstatusRepository extends JpaRepository<EstatusEntity, Integer> {
    public List<EstatusEntity> findByFolioOrderByFecha(Integer Folio);

    @Query("select " +
            "e.Id, " +
            "e.Folio, " +
            "e.IdEstatus, " +
            "ce.Nombre," +
            "e.Anotacion, " +
            "e.IdUsuario, " +
            "cs.NombrePropio," +
            "e.Fecha " +
            "from estatus e,catalogo_estatus ce, catalogo_usuarios_soporte cs " +
            "where e.Folio = :folio " +
            "AND ce.Id = e.IdEstatus " +
            "AND cs.Id = e.IdUsuario " +
            "ORDER BY e.Fecha")
    public List<EstatusDAO> findAllDAO(Integer folio);
}
