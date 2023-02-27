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

    @Query(value="SELECT " +
            "concentrado_folios_incidencias.Folio," +
            "catalogo_unidades.Nombre AS Unidad," +
            "catalogo_areas.Nombre AS Area," +
            "UsuarioReporta," +
            "catalogo_tipo_incidencias.Nombre AS Incidencia," +
            "catalogo_bien.Nombre AS Bien," +
            "Marca," +
            "Modelo," +
            "NumeroSerie," +
            "NumeroInventario," +
            "MotivoReporte," +
            "catalogo_usuarios_soporte.NombrePropio As SoporteAsignado," +
            "CASE Activo WHEN 0 THEN 'CERRADO' ELSE 'ABIERTO' END AS Estado," +
            "DatosEstatus.Nombre AS Estatus," +
            "DatosEstatus.Fecha " +
            "FROM concentrado_folios_incidencias,(SELECT Folio,IdEstatus,catalogo_estatus.Nombre,Fecha FROM estatus,catalogo_estatus " +
            "WHERE estatus.Id IN (SELECT MAX(Id) AS MaxId FROM estatus GROUP BY Folio) AND IdEstatus = catalogo_estatus.Id) AS DatosEstatus, " +
            "catalogo_unidades, catalogo_areas,catalogo_tipo_incidencias, catalogo_bien, catalogo_usuarios_soporte " +
            "WHERE concentrado_folios_incidencias.Folio = DatosEstatus.Folio " +
            "AND concentrado_folios_incidencias.IdUnidad = catalogo_unidades.Id " +
            "AND concentrado_folios_incidencias.IdArea = catalogo_areas.Id " +
            "AND concentrado_folios_incidencias.IdTipoIncidencia = catalogo_tipo_incidencias.Id " +
            "AND concentrado_folios_incidencias.IdBien = catalogo_bien.Id " +
            "AND concentrado_folios_incidencias.IdUsuarioSoporteAsignado = catalogo_usuarios_soporte.Id " +
            "ORDER BY concentrado_folios_incidencias.Folio DESC", nativeQuery=true)
    List<FolioDAO> getAll();
}
