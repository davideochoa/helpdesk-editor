package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.datos.entity.BiendEntity;
import com.helpdeskeditor.application.app.facade.FolioIncidenciaFacade;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
