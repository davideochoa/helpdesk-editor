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

import com.helpdeskeditor.application.app.data.repository.FoliosRepository;

import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class FoliosFacade {
    private final FoliosRepository foliosRepository;

    public FoliosFacade(FoliosRepository foliosRepository) {
        this.foliosRepository = foliosRepository;
    }

    @Transactional(readOnly = true)
    public List<String> getAllUsuarioReporta(){
        return  foliosRepository.getAllUsuarioReporta();
    }

    @Transactional(readOnly = true)
    public List<String> getAllMarca(){
        return  foliosRepository.getAllMarca();
    }

    public List<String> findMarcaByIdIncidenciaAndIdBien(Integer idIncidencia,Integer idBien){
        return  foliosRepository.findMarcaByIdIncidenciaAndIdBien(idIncidencia,idBien);
    }

    @Transactional(readOnly = true)
    public List<String> getAllModelo(){
        return  foliosRepository.getAllModelo();
    }

    @Transactional(readOnly = true)
    public List<String> findModeloByMarca(String marca){
        return  foliosRepository.findModeloByMarca(marca);
    }

    @Transactional(readOnly = true)
    public List<String> findModeloByIdIncidenciaAndIdBienAndMarca(Integer idIncidencia, Integer idBien,String marca){
        return  foliosRepository.findModeloByIdIncidenciaAndIdBienAndMarca(idIncidencia, idBien, marca);
    }

    @Transactional(readOnly = true)
    public List<String> findSerieByIdIncidenciaAndIdBienAndMarcaAndModelo(Integer idIncidencia,
                                                                          Integer idBien,
                                                                          String marca,
                                                                          String modelo){
        return  foliosRepository.findSerieByIdIncidenciaAndIdBienAndMarcaAndModelo(idIncidencia, idBien, marca, modelo);
    }

    @Transactional(readOnly = true)
    public List<String> findSerieByIdIncidenciaAndIdBienAndMarcaAndModeloAndNumeroSerie(Integer idIncidencia,
                                                                                        Integer idBien,
                                                                                        String marca,
                                                                                        String modelo,
                                                                                        String numeroSerie){
        return  foliosRepository.findSerieByIdIncidenciaAndIdBienAndMarcaAndModeloAndNumeroSerie(idIncidencia, idBien,
                                                                                                marca, modelo,
                                                                                                numeroSerie);
    }

    @Transactional(readOnly = true)
    public Optional<FolioEntity> findById(Integer Id){
        return foliosRepository.findById(Id);
    }

    @Transactional
    public FolioEntity save(FolioEntity folioEntity){ return foliosRepository.save(folioEntity);
    }

    @Transactional(readOnly = true)
    public List<FolioDAO> getAll(){
        return foliosRepository.getAll();
    }

    @Transactional(readOnly = true)
    public List<FolioDAO> getByIdUsuarioSoporteAsignado(Integer idUsuarioSoporteAsignado){
        return foliosRepository.getByIdUsuarioSoporteAsignado(idUsuarioSoporteAsignado);
    }

    @Transactional(readOnly = true)
    public List<FoliosxUnidadDTO> getFoliosXUnidad(Date LDfechaInicio, Date LDfechaFin){
        return foliosRepository.getFoliosXUnidad(LDfechaInicio, LDfechaFin);
    }

    @Transactional(readOnly = true)
    public List<FolioxIncidenciaDTO> getFoliosXIncidencia(Date LDfechaInicio, Date LDfechaFin){
        return foliosRepository.getFoliosXIncidencia(LDfechaInicio, LDfechaFin);
    }

    @Transactional(readOnly = true)
    public List<IncidenciaXUnidad> getFoliosIncidenciaXUnidad(Date LDfechaInicio, Date LDfechaFin){
        return foliosRepository.getFoliosIncidenciaXUnidad(LDfechaInicio,LDfechaFin);
    }

    @Transactional(readOnly = true)
    public List<IncidenciaXUnidad> getFoliosIncidenciaXBien(Date LDfechaInicio, Date LDfechaFin){
        return foliosRepository.getFoliosIncidenciaXBien(LDfechaInicio,LDfechaFin);
    }

    @Transactional(readOnly = true)
    public List<DatosCategoriasSeriesDAO> getCantidadFoliosGeneradosXMes(Date LDfechaInicio, Date LDfechaFin){
        return foliosRepository.getCantidadFoliosGeneradosXMes(LDfechaInicio,LDfechaFin);
    }

    @Transactional(readOnly = true)
    public List<DatosParaGraficaLineal> getDatosGraficaLineal(Date LDfechaInicio, Date LDfechaFin){
        List<Tuple> tupla = foliosRepository.getDatosGraficaLineal(LDfechaInicio,LDfechaFin);

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

    @Transactional(readOnly = true)
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

        return foliosRepository.getValoresGraficaLineal(LDfechaInicio,LDfechaFin);
    }
}
