package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.domain.entity.BiendEntity;
import com.helpdeskeditor.application.app.domain.entity.UnidadEntity;
import com.helpdeskeditor.application.app.facade.BienFacade;
import com.helpdeskeditor.application.app.facade.UnidadFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BienService {
    private BienFacade bienFacade;

    public BienService(BienFacade bienFacade) {
        this.bienFacade = bienFacade;
    }

    public List<BiendEntity> getAllBienes(){
        return bienFacade.getAllBienes();
    }
}
