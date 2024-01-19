package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.data.entity.UnidadEntity;
import com.helpdeskeditor.application.app.facade.UnidadesFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadesService {
    private static UnidadesFacade unidadesFacade;
    public UnidadesService(UnidadesFacade unidadesFacade) {
        this.unidadesFacade = unidadesFacade;
    }

    public List<UnidadEntity> findAll(){
        return unidadesFacade.findAll();
    }

    public Optional<UnidadEntity> findById(Integer Id){
        return unidadesFacade.findById(Id);
    }

    public UnidadEntity save(UnidadEntity unidadEntity){
        return unidadesFacade.save(unidadEntity);
    }


}
