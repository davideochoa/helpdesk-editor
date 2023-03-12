package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.data.entity.UnidadEntity;
import com.helpdeskeditor.application.app.facade.UnidadFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadService {
    private static UnidadFacade unidadFacade;
    public UnidadService(UnidadFacade unidadFacade) {
        this.unidadFacade = unidadFacade;
    }

    public List<UnidadEntity> findAll(){
        return unidadFacade.findAll();
    }

    public Optional<UnidadEntity> findById(Integer Id){
        return unidadFacade.findById(Id);
    }

    public UnidadEntity save(UnidadEntity unidadEntity){
        return unidadFacade.save(unidadEntity);
    }
}
