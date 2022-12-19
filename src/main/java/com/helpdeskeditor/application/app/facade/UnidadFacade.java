package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.datos.entity.UnidadEntity;
import com.helpdeskeditor.application.app.datos.repository.UnidadRepository;
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

    public List<UnidadEntity> findAll(){
        return unidadRepository.findAll();
    }
}
