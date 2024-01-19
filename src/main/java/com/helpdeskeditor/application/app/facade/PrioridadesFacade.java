package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.entity.PrioridadEntity;
import com.helpdeskeditor.application.app.data.repository.PrioridadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional(readOnly = true)
public class PrioridadesFacade {
    private PrioridadesRepository prioridadesRepository;

    @Autowired
    public PrioridadesFacade(PrioridadesRepository prioridadesRepository) {
        this.prioridadesRepository = prioridadesRepository;
    }

    public List<PrioridadEntity> findAll(){
        return prioridadesRepository.findAll();
    }

    public Optional<PrioridadEntity> findById(Integer Id){
        return prioridadesRepository.findById(Id);
    }
}
