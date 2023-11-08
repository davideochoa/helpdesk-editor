package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.data.DAO.DatosCategoriasSeriesDAO;
import com.helpdeskeditor.application.app.data.DAO.DatosParaGraficaLineal;
import com.helpdeskeditor.application.app.data.DAO.FolioDAO;
import com.helpdeskeditor.application.app.data.DAO.FoliosxUnidadDTO;
import com.helpdeskeditor.application.app.data.DAO.FolioxIncidenciaDTO;
import com.helpdeskeditor.application.app.data.DAO.IncidenciaXUnidad;
import com.helpdeskeditor.application.app.data.DAO.ValoresParaGraficaLineal;
import com.helpdeskeditor.application.app.data.entity.FolioEntity;
import com.helpdeskeditor.application.app.facade.FolioFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FolioService {

    private final FolioFacade  folioFacade;

    @Autowired
    public FolioService(FolioFacade folioFacade) {
        this.folioFacade = folioFacade;
    }

    public List<String> getAllUsuarioReporta(){
        return folioFacade.getAllUsuarioReporta();
    }

    public List<String> getAllMarca(){
        return folioFacade.getAllMarca();
    }

    public List<String> findMarcaByIdIncidenciaAndIdBien(Integer idIncidencia,Integer idBien){
        return  folioFacade.findMarcaByIdIncidenciaAndIdBien(idIncidencia,idBien);
    }

    public List<String> getAllModelo(){
        return folioFacade.getAllModelo();
    }

    public List<String> findModeloByMarca(String marca){
        return  folioFacade.findModeloByMarca(marca);
    }

    public List<String> findModeloByIdIncidenciaAndIdBienAndMarca(Integer idIncidencia, Integer idBien,String marca){
        return  folioFacade.findModeloByIdIncidenciaAndIdBienAndMarca(idIncidencia, idBien, marca);
    }

    public Optional<FolioEntity> findById(Integer Id){
        return folioFacade.findById(Id);
    }

    public FolioEntity save(FolioEntity folioEntity){ return folioFacade.save(folioEntity);}

    public List<FolioDAO> getAll(){
        return folioFacade.getAll();
    }

    public List<FolioDAO> getByIdUsuarioSoporteAsignado(Integer idUsuarioSoporteAsignado){
        return folioFacade.getByIdUsuarioSoporteAsignado(idUsuarioSoporteAsignado);
    }

    public List<FoliosxUnidadDTO> getFoliosXUnidad(Date LDfechaInicio, Date LDfechaFin){
        return folioFacade.getFoliosXUnidad(LDfechaInicio, LDfechaFin);
    }

    public List<FolioxIncidenciaDTO> getFoliosXIncidencia(Date LDfechaInicio, Date LDfechaFin){
        return folioFacade.getFoliosXIncidencia(LDfechaInicio, LDfechaFin);
    }

    public List<IncidenciaXUnidad> getFoliosIncidenciaXUnidad(Date LDfechaInicio, Date LDfechaFin){
        return folioFacade.getFoliosIncidenciaXUnidad(LDfechaInicio,LDfechaFin);
    }

    public List<IncidenciaXUnidad> getFoliosIncidenciaXBien(Date LDfechaInicio, Date LDfechaFin){
        return folioFacade.getFoliosIncidenciaXBien(LDfechaInicio,LDfechaFin);
    }

    public List<DatosCategoriasSeriesDAO> getCantidadFoliosGeneradosXMes(Date LDfechaInicio, Date LDfechaFin){
        return folioFacade.getCantidadFoliosGeneradosXMes(LDfechaInicio,LDfechaFin);
    }

    public List<DatosParaGraficaLineal> getDatosGraficaLineal(Date LDfechaInicio, Date LDfechaFin){
        return folioFacade.getDatosGraficaLineal(LDfechaInicio,LDfechaFin);
    }

    public List<ValoresParaGraficaLineal> getValoresGraficaLineal(Date LDfechaInicio, Date LDfechaFin){
        return folioFacade.getValoresGraficaLineal(LDfechaInicio,LDfechaFin);
    }
}
