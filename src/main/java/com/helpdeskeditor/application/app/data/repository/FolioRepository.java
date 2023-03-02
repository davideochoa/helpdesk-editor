package com.helpdeskeditor.application.app.data.repository;

import com.helpdeskeditor.application.app.data.DAO.FolioDAO;
import com.helpdeskeditor.application.app.data.entity.FolioEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolioRepository extends CrudRepository<FolioEntity, Integer> {

    @Query("SELECT CFI.usuarioReporta "+
            "FROM FolioEntity CFI "+
            "GROUP BY CFI.usuarioReporta ORDER BY CFI.usuarioReporta ASC")
    List<String> getAllUsuarioReporta();

    @Query("SELECT CFI.marca "+
            "FROM FolioEntity CFI "+
            "GROUP BY CFI.marca ORDER BY CFI.marca ASC")
    List<String> getAllMarca();

    @Query("SELECT CFI.marca "+
            "FROM FolioEntity CFI "+
            "WHERE CFI.idTipoIncidencia = :idIncidencia "+
            "AND CFI.idBien = :idBien "+
            "GROUP BY CFI.marca ORDER BY CFI.marca ASC")
    List<String> findMarcaByIdIncidenciaAndIdBien(Integer idIncidencia, Integer idBien);

    @Query("SELECT CFI.modelo "+
            "FROM FolioEntity CFI "+
            "GROUP BY CFI.modelo ORDER BY CFI.modelo ASC")
    List<String> getAllModelo();

    @Query("SELECT CFI.modelo "+
            "FROM FolioEntity CFI "+
            "WHERE CFI.marca = :marca "+
            "GROUP BY CFI.modelo ORDER BY CFI.modelo ASC")
    List<String> findModeloByMarca(String marca);

    @Query("SELECT CFI.modelo "+
            "FROM FolioEntity CFI "+
            "WHERE CFI.idTipoIncidencia = :idIncidencia "+
            "AND CFI.idBien = :idBien "+
            "AND CFI.marca = :marca "+
            "GROUP BY CFI.modelo ORDER BY CFI.modelo ASC")
    List<String> findModeloByIdIncidenciaAndIdBienAndMarca(Integer idIncidencia, Integer idBien,String marca);

    @Query(value="SELECT new com.helpdeskeditor.application.app.data.DAO.FolioDAO( "+

            "fe.id,"+
            "ue.nombre AS unidad,"+
            "fe.usuarioReporta, fe.marca, fe.modelo, fe.numeroSerie, fe.numeroInventario,"+
            "CASE fe.activo WHEN 0 THEN 'CERRADO' ELSE 'ABIERTO' END AS estado "+

            ") " +

            "FROM FolioEntity fe,UnidadEntity ue "+
            "WHERE fe.idUnidad = ue.id "+
            "ORDER BY fe.id DESC", nativeQuery=false)
    List<FolioDAO> getAll();

    /*
      @Query(value="SELECT Folio,Unidad,Area,Incidencia,Bien,Marca,Modelo,NumeroSerie,NumeroInventario,MotivoReporte,Estado
        FROM
        (select
        concentrado_folios_incidencias.Folio,
        catalogo_unidades.Nombre AS Unidad,
        catalogo_areas.Nombre AS Area,
        catalogo_tipo_incidencias.Nombre AS Incidencia,
        catalogo_bien.Nombre AS Bien,
        Marca,
        Modelo,
        NumeroSerie,
        NumeroInventario,
        MotivoReporte,
        CASE Activo WHEN 0 THEN 'CERRADO' ELSE 'ABIERTO' END AS Estado
        FROM concentrado_folios_incidencias,catalogo_unidades, catalogo_areas,catalogo_tipo_incidencias, catalogo_bien
        WHERE concentrado_folios_incidencias.IdUnidad = catalogo_unidades.Id
        AND concentrado_folios_incidencias.IdArea = catalogo_areas.Id
        AND concentrado_folios_incidencias.IdTipoIncidencia = catalogo_tipo_incidencias.Id
        AND concentrado_folios_incidencias.IdBien = catalogo_bien.Id) AS tabla
        ORDER BY Folio DESC", nativeQuery=true)
    */
}
