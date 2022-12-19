package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.datos.entity.UnidadEntity;
import com.helpdeskeditor.application.app.facade.UnidadFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadService {
    private UnidadFacade unidadFacade;
    public UnidadService(UnidadFacade unidadFacade) {
        this.unidadFacade = unidadFacade;
    }

    public List<UnidadEntity> findAll(){
        return unidadFacade.findAll();
    }
}
