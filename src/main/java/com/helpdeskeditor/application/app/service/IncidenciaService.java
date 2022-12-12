package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.datos.entity.IncidenciaEntity;
import com.helpdeskeditor.application.app.facade.IncidenciaFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidenciaService {
    private IncidenciaFacade incidenciaFacade;
    public IncidenciaService(IncidenciaFacade incidenciaFacade) {
        this.incidenciaFacade = incidenciaFacade;
    }

    public List<IncidenciaEntity> getAllIncidencias(){
        return incidenciaFacade.getAllIncidencias();
    }
}
