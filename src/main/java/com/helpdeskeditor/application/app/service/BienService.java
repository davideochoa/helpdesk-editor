package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.data.entity.BiendEntity;
import com.helpdeskeditor.application.app.facade.BienFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BienService {
    private BienFacade bienFacade;

    public BienService(BienFacade bienFacade) {
        this.bienFacade = bienFacade;
    }

    public List<BiendEntity> findAll(){
        return bienFacade.findAll();
    }

    public List<BiendEntity> findByidTipoIncidencia(int idTipoIncidencia){
        return bienFacade.findByidTipoIncidencia(idTipoIncidencia);
    }
}
