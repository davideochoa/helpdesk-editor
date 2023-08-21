package com.helpdeskeditor.application.app.data.repository;

import com.helpdeskeditor.application.app.data.DAO.FolioDAO;
import com.helpdeskeditor.application.app.data.DAO.FoliosxUnidadDTO;
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

    @Query(value="SELECT new com.helpdeskeditor.application.app.data.DAO.FolioDAO("+
            "fe.id,"+
            "fe.fecha,"+
            "ue.nombre AS unidad,"+
            "fe.usuarioReporta, "+
            "be.nombre AS bien,"+
            "fe.marca, fe.modelo, fe.numeroSerie, fe.numeroInventario,"+
            "CASE fe.activo WHEN 0 THEN 'CERRADO' ELSE 'ABIERTO' END AS estado"+
            ") " +
            "FROM FolioEntity fe,UnidadEntity ue, BienEntity be "+
            "WHERE fe.idUnidad = ue.id "+
            "AND fe.idBien = be.id "+
            "ORDER BY estado,fe.id DESC", nativeQuery=false)
    List<FolioDAO> getAll();


    @Query(value="SELECT new com.helpdeskeditor.application.app.data.DAO.FolioDAO("+
            "fe.id,"+
            "fe.fecha,"+
            "ue.nombre AS unidad,"+
            "fe.usuarioReporta, "+
            "be.nombre AS bien,"+
            "fe.marca, fe.modelo, fe.numeroSerie, fe.numeroInventario,"+
            "CASE fe.activo WHEN 0 THEN 'CERRADO' ELSE 'ABIERTO' END AS estado"+
            ") " +
            "FROM FolioEntity fe,UnidadEntity ue, BienEntity be "+
            "WHERE fe.idUnidad = ue.id "+
            "AND fe.idUsuarioSoporteAsignado = :idUsuarioSoporteAsignado "+
            "AND fe.idBien = be.id "+
            "ORDER BY estado,fe.id DESC", nativeQuery=false)
    List<FolioDAO> getByIdUsuarioSoporteAsignado(Integer idUsuarioSoporteAsignado);

    @Query(value = "SELECT new com.helpdeskeditor.application.app.data.DAO.FoliosxUnidadDTO("+
            "ue.nombre, "+
            "COUNT(fe.idUnidad) AS cantidadFolios) "+
            "FROM FolioEntity fe,UnidadEntity ue "+
            "WHERE fe.fecha BETWEEN '2023-01-01 00:00:00' AND '2023-01-31 23:59:59' "+
            "AND fe.idUnidad = ue.id "+
            "GROUP BY fe.idUnidad,ue.nombre  "+
            "ORDER BY cantidadFolios DESC, ue.nombre",nativeQuery=false)
    List<FoliosxUnidadDTO> getFoliosXUnidad();
/*
SELECT catalogo_unidades.Nombre,tabla1.cantidadFolios FROM
(SELECT IdUnidad, COUNT(IdUnidad) AS cantidadFolios FROM concentrado_folios_incidencias
WHERE fecha BETWEEN '2023-01-01 00:00:00' AND '2023-12-31 23:59:59'
GROUP BY IdUnidad) AS tabla1,
catalogo_unidades
WHERE tabla1.IdUnidad = catalogo_unidades.Id
ORDER BY Nombre
*/

}
