package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.datos.entity.IncidenciaEntity;
import com.helpdeskeditor.application.app.datos.repository.IncidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class IncidenciaFacade {
    private IncidenciaRepository incidenciaRepository;

    @Autowired
    public IncidenciaFacade(IncidenciaRepository incidenciaRepository) {
        this.incidenciaRepository = incidenciaRepository;
    }

    public List<IncidenciaEntity> getAllIncidencias(){
        return incidenciaRepository.findAll();
    }
}
