package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.data.DAO.DatosCategoriasSeriesDAO;
import com.helpdeskeditor.application.app.data.DAO.GraficaLineal.DatosParaGraficaLineal;
import com.helpdeskeditor.application.app.data.DAO.FolioDAO;
import com.helpdeskeditor.application.app.data.DAO.FoliosxUnidadDTO;
import com.helpdeskeditor.application.app.data.DAO.FolioxIncidenciaDTO;
import com.helpdeskeditor.application.app.data.DAO.IncidenciaXUnidad;
import com.helpdeskeditor.application.app.data.DAO.GraficaLineal.ValoresParaGraficaLineal;
import com.helpdeskeditor.application.app.data.entity.FolioEntity;
import com.helpdeskeditor.application.app.facade.FoliosFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FoliosService {

    private final FoliosFacade foliosFacade;

    @Autowired
    public FoliosService(FoliosFacade foliosFacade) {
        this.foliosFacade = foliosFacade;
    }

    public List<String> getAllUsuarioReporta(){
        return foliosFacade.getAllUsuarioReporta();
    }

    public List<String> getAllMarca(){
        return foliosFacade.getAllMarca();
    }

    public List<String> findMarcaByIdIncidenciaAndIdBien(Integer idIncidencia,Integer idBien){
        return  foliosFacade.findMarcaByIdIncidenciaAndIdBien(idIncidencia,idBien);
    }

    public List<String> getAllModelo(){
        return foliosFacade.getAllModelo();
    }

    public List<String> findModeloByMarca(String marca){
        return  foliosFacade.findModeloByMarca(marca);
    }

    public List<String> findModeloByIdIncidenciaAndIdBienAndMarca(Integer idIncidencia, Integer idBien,String marca){
        return  foliosFacade.findModeloByIdIncidenciaAndIdBienAndMarca(idIncidencia, idBien, marca);
    }

    public List<String> findSerieByIdIncidenciaAndIdBienAndMarcaAndModelo(Integer idIncidencia, Integer idBien,String marca, String modelo){
        return  foliosFacade.findSerieByIdIncidenciaAndIdBienAndMarcaAndModelo(idIncidencia, idBien, marca, modelo);
    }

    public List<String> findSerieByIdIncidenciaAndIdBienAndMarcaAndModeloAndNumeroSerie(Integer idIncidencia,
                                                                                        Integer idBien,
                                                                                        String marca,
                                                                                        String modelo,
                                                                                        String numeroSerie){
        return  foliosFacade.findSerieByIdIncidenciaAndIdBienAndMarcaAndModeloAndNumeroSerie(idIncidencia, idBien,
                                                                                            marca, modelo,
                                                                                            numeroSerie);
    }

    public Optional<FolioEntity> findById(Integer Id){
        return foliosFacade.findById(Id);
    }

    public FolioEntity save(FolioEntity folioEntity){ return foliosFacade.save(folioEntity);}

    public List<FolioDAO> getAll(){
        return foliosFacade.getAll();
    }

    public List<FolioDAO> getByIdUsuarioSoporteAsignado(Integer idUsuarioSoporteAsignado){
        return foliosFacade.getByIdUsuarioSoporteAsignado(idUsuarioSoporteAsignado);
    }

    public List<FoliosxUnidadDTO> getFoliosXUnidad(Date LDfechaInicio, Date LDfechaFin){
        return foliosFacade.getFoliosXUnidad(LDfechaInicio, LDfechaFin);
    }

    public List<FolioxIncidenciaDTO> getFoliosXIncidencia(Date LDfechaInicio, Date LDfechaFin){
        return foliosFacade.getFoliosXIncidencia(LDfechaInicio, LDfechaFin);
    }

    public List<IncidenciaXUnidad> getFoliosIncidenciaXUnidad(Date LDfechaInicio, Date LDfechaFin){
        return foliosFacade.getFoliosIncidenciaXUnidad(LDfechaInicio,LDfechaFin);
    }

    public List<IncidenciaXUnidad> getFoliosIncidenciaXBien(Date LDfechaInicio, Date LDfechaFin){
        return foliosFacade.getFoliosIncidenciaXBien(LDfechaInicio,LDfechaFin);
    }

    public List<DatosCategoriasSeriesDAO> getCantidadFoliosGeneradosXMes(Date LDfechaInicio, Date LDfechaFin){
        return foliosFacade.getCantidadFoliosGeneradosXMes(LDfechaInicio,LDfechaFin);
    }

    public List<DatosParaGraficaLineal> getDatosGraficaLineal(Date LDfechaInicio, Date LDfechaFin){

        List<DatosParaGraficaLineal> datosParaGraficaLinealList = foliosFacade.getDatosGraficaLineal(LDfechaInicio,LDfechaFin);
        List<ValoresParaGraficaLineal> valoresParaGraficaLinealList = foliosFacade.getValoresGraficaLineal(LDfechaInicio,LDfechaFin);

        for(ValoresParaGraficaLineal valoresParaGraficaLineal : valoresParaGraficaLinealList){
            for(DatosParaGraficaLineal datosParaGraficaLineal : datosParaGraficaLinealList){
                if(valoresParaGraficaLineal.getIdUnidad().equals(datosParaGraficaLineal.getIdUnidad()) &&
                        valoresParaGraficaLineal.getAnno().equals(datosParaGraficaLineal.getAnno()) &&
                        valoresParaGraficaLineal.getMesNumero().equals(datosParaGraficaLineal.getMesNumero())){
                    datosParaGraficaLineal.setValor(valoresParaGraficaLineal.getValor());
                    break;
                }
            }
        }

        return datosParaGraficaLinealList;
    }

}
