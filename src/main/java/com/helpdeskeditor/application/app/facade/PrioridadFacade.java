package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.entity.PrioridadEntity;
import com.helpdeskeditor.application.app.data.repository.PrioridadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class PrioridadFacade {
    private PrioridadRepository prioridadRepository;

    @Autowired
    public PrioridadFacade(PrioridadRepository prioridadRepository) {
        this.prioridadRepository = prioridadRepository;
    }

    public List<PrioridadEntity> findAll(){
        return prioridadRepository.findAll();
    }
}
