package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.data.DAO.FolioDAO;
import com.helpdeskeditor.application.app.data.entity.FolioEntity;
import com.helpdeskeditor.application.app.data.repository.FolioRepository;
import com.helpdeskeditor.application.app.facade.FolioFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
