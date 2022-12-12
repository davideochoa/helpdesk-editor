package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.facade.FolioIncidenciaFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolioIncidenciaService {

    FolioIncidenciaFacade folioIncidenciaFacade;
    public FolioIncidenciaService(FolioIncidenciaFacade folioIncidenciaFacade) {
        this.folioIncidenciaFacade = folioIncidenciaFacade;
    }

    public List<String> getUsuarioReporta(){
        return folioIncidenciaFacade.getUsuarioReporta();
    }


}
