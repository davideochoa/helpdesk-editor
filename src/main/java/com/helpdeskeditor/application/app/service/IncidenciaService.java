package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.data.entity.IncidenciaEntity;
import com.helpdeskeditor.application.app.facade.IncidenciaFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidenciaService {
    private IncidenciaFacade incidenciaFacade;
    public IncidenciaService(IncidenciaFacade incidenciaFacade) {
        this.incidenciaFacade = incidenciaFacade;
    }

    public List<IncidenciaEntity> findAll(){
        return incidenciaFacade.findAll();
    }

    public Optional<IncidenciaEntity> findById(Integer Id){
        return incidenciaFacade.findById(Id);
    }
}
