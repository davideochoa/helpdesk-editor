package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.data.entity.CatalogoEstatusEntity;
import com.helpdeskeditor.application.app.facade.CatalogosEstatusFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogoEstatusService {
    private CatalogosEstatusFacade catalogosEstatusFacade;
    public CatalogoEstatusService(CatalogosEstatusFacade catalogosEstatusFacade) {
        this.catalogosEstatusFacade = catalogosEstatusFacade;
    }

    public List<CatalogoEstatusEntity> findAll(){
        return catalogosEstatusFacade.findAll();
    }
}
