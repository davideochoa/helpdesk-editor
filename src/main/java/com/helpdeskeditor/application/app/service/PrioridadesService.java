package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.data.entity.PrioridadEntity;
import com.helpdeskeditor.application.app.facade.PrioridadesFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrioridadesService {
    private PrioridadesFacade prioridadesFacade;
    public PrioridadesService(PrioridadesFacade prioridadesFacade) {
        this.prioridadesFacade = prioridadesFacade;
    }

    public List<PrioridadEntity> findAll(){
        return prioridadesFacade.findAll();
    }

    public Optional<PrioridadEntity> findById(Integer Id){
        return prioridadesFacade.findById(Id);
    }
}
