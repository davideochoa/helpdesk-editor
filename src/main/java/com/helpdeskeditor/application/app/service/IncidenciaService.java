package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.domain.entity.IncidenciaEntity;
import com.helpdeskeditor.application.app.domain.entity.UnidadEntity;
import com.helpdeskeditor.application.app.facade.IncidenciaFacade;
import com.helpdeskeditor.application.app.facade.UnidadFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidenciaService {
    private IncidenciaFacade incidenciaFacade;
    public IncidenciaService(IncidenciaFacade incidenciaFacade) {
        this.incidenciaFacade = incidenciaFacade;
    }

    public List<IncidenciaEntity> incidenciaFacade(){
        return incidenciaFacade.getAllIncidencias();
    }
}
