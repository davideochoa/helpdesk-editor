package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.data.entity.FolioIncidenciaEntity;
import com.helpdeskeditor.application.app.facade.FolioIncidenciaFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FolioIncidenciaService {

    FolioIncidenciaFacade folioIncidenciaFacade;
    public FolioIncidenciaService(FolioIncidenciaFacade folioIncidenciaFacade) {
        this.folioIncidenciaFacade = folioIncidenciaFacade;
    }

    public List<String> getAllUsuarioReporta(){
        return folioIncidenciaFacade.getAllUsuarioReporta();
    }

    public List<String> getAllMarca(){
        return folioIncidenciaFacade.getAllMarca();
    }

    public List<String> getAllModelo(){
        return folioIncidenciaFacade.getAllModelo();
    }

    public List<String> findModeloByMarca(String marca){
        return  folioIncidenciaFacade.findModeloByMarca(marca);
    }

    public Optional<FolioIncidenciaEntity> findById(Integer Id){
        return folioIncidenciaFacade.findById(Id);
    }
}
