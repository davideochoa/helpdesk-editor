package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.domain.entity.UnidadEntity;
import com.helpdeskeditor.application.app.domain.repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class UnidadFacade {
    private UnidadRepository unidadRepository;

    @Autowired
    public UnidadFacade(UnidadRepository unidadRepository) {
        this.unidadRepository = unidadRepository;
    }

    public List<UnidadEntity> getAllUnidades(){
        return unidadRepository.findAll();
    }
}
