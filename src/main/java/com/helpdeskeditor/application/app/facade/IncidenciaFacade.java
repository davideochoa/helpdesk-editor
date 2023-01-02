package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.entity.IncidenciaEntity;
import com.helpdeskeditor.application.app.data.repository.IncidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional(readOnly = true)
public class IncidenciaFacade {
    private IncidenciaRepository incidenciaRepository;

    @Autowired
    public IncidenciaFacade(IncidenciaRepository incidenciaRepository) {
        this.incidenciaRepository = incidenciaRepository;
    }

    public List<IncidenciaEntity> findAll(){
        return incidenciaRepository.findAll(Sort.by(Sort.Direction.ASC,"Nombre"));
    }

    public Optional<IncidenciaEntity> findById(Integer Id){
        return incidenciaRepository.findById(Id);
    }
}
