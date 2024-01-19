package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.data.entity.IncidenciaEntity;
import com.helpdeskeditor.application.app.facade.IncidenciasFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidenciaService {
    private IncidenciasFacade incidenciasFacade;
    public IncidenciaService(IncidenciasFacade incidenciasFacade) {
        this.incidenciasFacade = incidenciasFacade;
    }

    public List<IncidenciaEntity> findAll(){
        return incidenciasFacade.findAll();
    }

    public Optional<IncidenciaEntity> findById(Integer Id){
        return incidenciasFacade.findById(Id);
    }

    public IncidenciaEntity save(IncidenciaEntity incidenciaEntity){
        return incidenciasFacade.save(incidenciaEntity);
    }
}
