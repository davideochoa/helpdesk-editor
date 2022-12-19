package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.datos.entity.CatalogoEstatusEntity;
import com.helpdeskeditor.application.app.facade.CatalogoEstatusFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogoEstatusService {
    private CatalogoEstatusFacade catalogoEstatusFacade;
    public CatalogoEstatusService(CatalogoEstatusFacade catalogoEstatusFacade) {
        this.catalogoEstatusFacade = catalogoEstatusFacade;
    }

    public List<CatalogoEstatusEntity> findAll(){
        return catalogoEstatusFacade.findAll();
    }
}
