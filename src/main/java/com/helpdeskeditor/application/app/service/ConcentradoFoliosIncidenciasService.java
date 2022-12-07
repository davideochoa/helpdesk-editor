package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.domain.entity.ConcentradoFolioIncidencia;
import com.helpdeskeditor.application.app.domain.repository.ConcentradoFoliosIncidenciasRepository;
import com.helpdeskeditor.application.app.facade.ConcentradoFoliosIncidenciasFacade;

import java.util.List;

public class ConcentradoFoliosIncidenciasService {

    ConcentradoFoliosIncidenciasFacade concentradoFoliosIncidenciasFacade;
    public ConcentradoFoliosIncidenciasService(ConcentradoFoliosIncidenciasFacade concentradoFoliosIncidenciasFacade) {
        this.concentradoFoliosIncidenciasFacade = concentradoFoliosIncidenciasFacade;
    }

    public List<ConcentradoFolioIncidencia> findusuarioReporta(){
        return concentradoFoliosIncidenciasFacade.findusuarioReporta();
    }
}
