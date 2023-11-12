package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.DAO.DatosCategoriasSeriesDAO;
import com.helpdeskeditor.application.app.data.DAO.GraficaLineal.DatosParaGraficaLineal;
import com.helpdeskeditor.application.app.data.DAO.FolioDAO;
import com.helpdeskeditor.application.app.data.DAO.FoliosxUnidadDTO;
import com.helpdeskeditor.application.app.data.DAO.FolioxIncidenciaDTO;
import com.helpdeskeditor.application.app.data.DAO.IncidenciaXUnidad;
import com.helpdeskeditor.application.app.data.DAO.GraficaLineal.ValoresParaGraficaLineal;
import com.helpdeskeditor.application.app.data.entity.FolioEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helpdeskeditor.application.app.data.repository.FolioRepository;

import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class FolioFacade {
    private final FolioRepository folioRepository;

    public FolioFacade(FolioRepository folioRepository) {
        this.folioRepository = folioRepository;
    }

    @Transactional(readOnly = true)
    public List<String> getAllUsuarioReporta(){
        return  folioRepository.getAllUsuarioReporta();
    }

    @Transactional(readOnly = true)
    public List<String> getAllMarca(){
        return  folioRepository.getAllMarca();
    }

    public List<String> findMarcaByIdIncidenciaAndIdBien(Integer idIncidencia,Integer idBien){
        return  folioRepository.findMarcaByIdIncidenciaAndIdBien(idIncidencia,idBien);
    }

    @Transactional(readOnly = true)
    public List<String> getAllModelo(){
        return  folioRepository.getAllModelo();
    }

    @Transactional(readOnly = true)
    public List<String> findModeloByMarca(String marca){
        return  folioRepository.findModeloByMarca(marca);
    }

    public List<String> findModeloByIdIncidenciaAndIdBienAndMarca(Integer idIncidencia, Integer idBien,String marca){
        return  folioRepository.findModeloByIdIncidenciaAndIdBienAndMarca(idIncidencia, idBien, marca);
    }

    @Transactional(readOnly = true)
    public Optional<FolioEntity> findById(Integer Id){
        return folioRepository.findById(Id);
    }

    @Transactional
    public FolioEntity save(FolioEntity folioEntity){ return folioRepository.save(folioEntity);
    }

    @Transactional(readOnly = true)
    public List<FolioDAO> getAll(){
        return folioRepository.getAll();
    }

    @Transactional(readOnly = true)
    public List<FolioDAO> getByIdUsuarioSoporteAsignado(Integer idUsuarioSoporteAsignado){
        return folioRepository.getByIdUsuarioSoporteAsignado(idUsuarioSoporteAsignado);
    }

    public List<FoliosxUnidadDTO> getFoliosXUnidad(Date LDfechaInicio, Date LDfechaFin){
        return folioRepository.getFoliosXUnidad(LDfechaInicio, LDfechaFin);
    }

    public List<FolioxIncidenciaDTO> getFoliosXIncidencia(Date LDfechaInicio, Date LDfechaFin){
        return folioRepository.getFoliosXIncidencia(LDfechaInicio, LDfechaFin);
    }

    public List<IncidenciaXUnidad> getFoliosIncidenciaXUnidad(Date LDfechaInicio, Date LDfechaFin){
        return folioRepository.getFoliosIncidenciaXUnidad(LDfechaInicio,LDfechaFin);
    }

    public List<IncidenciaXUnidad> getFoliosIncidenciaXBien(Date LDfechaInicio, Date LDfechaFin){
        return folioRepository.getFoliosIncidenciaXBien(LDfechaInicio,LDfechaFin);
    }

    public List<DatosCategoriasSeriesDAO> getCantidadFoliosGeneradosXMes(Date LDfechaInicio, Date LDfechaFin){
        return folioRepository.getCantidadFoliosGeneradosXMes(LDfechaInicio,LDfechaFin);
    }

    public List<DatosParaGraficaLineal> getDatosGraficaLineal(Date LDfechaInicio, Date LDfechaFin){
        List<Tuple> tupla = folioRepository.getDatosGraficaLineal(LDfechaInicio,LDfechaFin);

        return tupla.stream()
                .map(tuple -> new DatosParaGraficaLineal(
                        tuple.get("idUnidad", Integer.class),
                        tuple.get("nombre", String.class),
                        tuple.get("anno", Integer.class),
                        tuple.get("mesNumero", Integer.class),
                        tuple.get("mesNombre", String.class),
                        tuple.get("valor", Integer.class)
                ))
                .toList();
    }

    public List<ValoresParaGraficaLineal> getValoresGraficaLineal(Date LDfechaInicio, Date LDfechaFin){
        /*List<Tuple> tupla = folioRepository.getValoresGraficaLineal(LDfechaInicio,LDfechaFin);

        return tupla.stream()
                .map(tuple -> new ValoresParaGraficaLineal(
                        tuple.get("idUnidad", Integer.class),
                        tuple.get("anno", Integer.class),
                        tuple.get("mesNumero", Integer.class),
                        tuple.get("valor", Integer.class)
                ))
                .toList();*/

        return folioRepository.getValoresGraficaLineal(LDfechaInicio,LDfechaFin);
    }
}
