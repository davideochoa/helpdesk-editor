package com.helpdeskeditor.application.app.data.repository;

import com.helpdeskeditor.application.app.data.DAO.SolicitudDAO;
import com.helpdeskeditor.application.app.data.entity.SolicitudEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudesRepository extends JpaRepository<SolicitudEntity, Integer> {

    @Query("select new com.helpdeskeditor.application.app.data.DAO.SolicitudDAO("+
            "se.id, "+
            "ae.nombre AS area, "+
            "be.nombre as tipoBien, "+
            "se.marca, "+
            "se.modelo, "+
            "se.numeroSerie, "+
            "se.numeroInventario, "+
            "se.motivo, "+
            "se.fecha,'ESTATUS AS status') "+

            "from SolicitudEntity se, AreaEntity ae, BienEntity be "+

            "WHERE se.idUnidad = :idUnidad "+
            "AND se.idArea = ae.id "+
            "AND se.idTipoBien = be.id "+

            "order by se.fecha, se.id")
    List<SolicitudDAO> findAllByIdUnidad(Integer idUnidad);

    @Query("select new com.helpdeskeditor.application.app.data.DAO.SolicitudDAO("+
            "se.id, "+
            "ae.nombre AS area, "+
            "be.nombre as tipoBien, "+
            "se.marca, "+
            "se.modelo, "+
            "se.numeroSerie, "+
            "se.numeroInventario, "+
            "se.motivo, "+
            "se.fecha) "+

            "from SolicitudEntity se, AreaEntity ae, BienEntity be "+

            "WHERE se.idUnidad = :idUnidad "+
            "AND se.idArea = ae.id "+
            "AND se.idTipoBien = be.id "+

            "order by se.fecha, se.id")
    Page<SolicitudDAO> findAllByIdUnidadPageable(Integer idUnidad, Pageable paginacion);
}
