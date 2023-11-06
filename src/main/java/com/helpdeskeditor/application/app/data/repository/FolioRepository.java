package com.helpdeskeditor.application.app.data.repository;

import com.helpdeskeditor.application.app.data.DAO.DatosCategoriasSeriesDAO;
import com.helpdeskeditor.application.app.data.DAO.FolioDAO;
import com.helpdeskeditor.application.app.data.DAO.FoliosxUnidadDTO;
import com.helpdeskeditor.application.app.data.DAO.FolioxIncidenciaDTO;
import com.helpdeskeditor.application.app.data.DAO.IncidenciaXUnidad;
import com.helpdeskeditor.application.app.data.entity.FolioEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
            "WHERE fe.fecha BETWEEN :LDfechaInicio AND :LDfechaFin "+
            "AND fe.idUnidad = ue.id "+
            "GROUP BY fe.idUnidad,ue.nombre  "+
            "ORDER BY ue.nombre ASC",nativeQuery=false)
    List<FoliosxUnidadDTO> getFoliosXUnidad(Date LDfechaInicio, Date LDfechaFin);

    @Query(value = "SELECT new com.helpdeskeditor.application.app.data.DAO.FolioxIncidenciaDTO("+
            "be.nombre, "+
            "COUNT(fe.idBien) AS cantidadIncidencias) "+
            "FROM FolioEntity fe,BienEntity be "+
            "WHERE fe.fecha BETWEEN :LDfechaInicio AND :LDfechaFin "+
            "AND fe.idBien = be.id "+
            "GROUP BY fe.idBien,be.nombre  "+
            "ORDER BY be.nombre ASC",nativeQuery=false)
    List<FolioxIncidenciaDTO> getFoliosXIncidencia(Date LDfechaInicio, Date LDfechaFin);

    @Query(value = "SELECT new com.helpdeskeditor.application.app.data.DAO.IncidenciaXUnidad("+
            "ue.nombre As unidad, "+
            "be.nombre AS bien, "+
            "COUNT(be.nombre) AS cantidadIncidencias) "+
            "FROM FolioEntity fe, UnidadEntity ue, BienEntity be "+
            "WHERE fe.fecha BETWEEN :LDfechaInicio AND :LDfechaFin "+
            "AND fe.idUnidad = ue.id AND fe.idBien = be.id "+
            "GROUP BY ue.nombre,be.nombre  "+
            "ORDER BY ue.nombre ASC, be.nombre ASC",nativeQuery=false)
    List<IncidenciaXUnidad> getFoliosIncidenciaXUnidad(Date LDfechaInicio, Date LDfechaFin);

    @Query(value = "SELECT new com.helpdeskeditor.application.app.data.DAO.IncidenciaXUnidad("+
            "be.nombre AS bien, "+
            "ue.nombre As unidad, "+
            "COUNT(ue.nombre) AS cantidadIncidencias) "+
            "FROM FolioEntity fe, UnidadEntity ue, BienEntity be "+
            "WHERE fe.fecha BETWEEN :LDfechaInicio AND :LDfechaFin "+
            "AND fe.idUnidad = ue.id AND fe.idBien = be.id "+
            "GROUP BY be.nombre,ue.nombre  "+
            "ORDER BY be.nombre ASC, ue.nombre ASC",nativeQuery=false)
    List<IncidenciaXUnidad> getFoliosIncidenciaXBien(Date LDfechaInicio, Date LDfechaFin);

    @Query(value = "SELECT new com.helpdeskeditor.application.app.data.DAO.DatosCategoriasSeriesDAO("+
            "YEAR(fe.fecha) as fecha, " +
            "CASE MONTH(fe.fecha) " +
                "WHEN 1 THEN 'ENE' " + "WHEN 2 THEN 'FEB' " +
                "WHEN 3 THEN 'MAR' " + "WHEN 4 THEN 'ABR' " +
                "WHEN 5 THEN 'MAY' " + "WHEN 6 THEN 'JUN' " +
                "WHEN 7 THEN 'JUL' " + "WHEN 8 THEN 'AGOS' " +
                "WHEN 9 THEN 'SEP' " + "WHEN 10 THEN 'OCT' " +
                "WHEN 11 THEN 'NOV' " + "WHEN 12 THEN 'DIC' "+
            "END AS categoria, " +
            "COUNT(MONTH(fe.fecha)) as data) "+
            "from FolioEntity fe " +
            "where fe.fecha BETWEEN :LDfechaInicio AND :LDfechaFin "+
            "GROUP BY YEAR(fe.fecha),MONTH(fe.fecha) " +
            "ORDER BY YEAR(fe.fecha),MONTH(fe.fecha)", nativeQuery=false)
    List<DatosCategoriasSeriesDAO> getCantidadFoliosGeneradosXMes(Date LDfechaInicio, Date LDfechaFin);


}
