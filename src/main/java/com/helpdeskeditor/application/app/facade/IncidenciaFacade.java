package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.domain.entity.IncidenciaEntity;
import com.helpdeskeditor.application.app.domain.entity.UnidadEntity;
import com.helpdeskeditor.application.app.domain.repository.IncidenciaRepository;
import com.helpdeskeditor.application.app.domain.repository.UnidadRepository;
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

    public List<IncidenciaEntity> getAllUnidades(){
        return incidenciaRepository.findAll();
    }
}
