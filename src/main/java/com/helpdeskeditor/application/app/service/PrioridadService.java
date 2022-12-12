package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.datos.entity.PrioridadEntity;
import com.helpdeskeditor.application.app.datos.entity.UnidadEntity;
import com.helpdeskeditor.application.app.facade.PrioridadFacade;
import com.helpdeskeditor.application.app.facade.UnidadFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrioridadService {
    private PrioridadFacade prioridadFacade;
    public PrioridadService(PrioridadFacade prioridadFacade) {
        this.prioridadFacade = prioridadFacade;
    }

    public List<PrioridadEntity> findAll(){
        return prioridadFacade.findAll();
    }
}
