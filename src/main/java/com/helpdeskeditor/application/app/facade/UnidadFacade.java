package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.entity.UnidadEntity;
import com.helpdeskeditor.application.app.data.repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional(readOnly = true)
public class UnidadFacade {
    private UnidadRepository unidadRepository;

    @Autowired
    public UnidadFacade(UnidadRepository unidadRepository) {
        this.unidadRepository = unidadRepository;
    }

    public List<UnidadEntity> findAll(){
        return unidadRepository.findAll(Sort.by(Sort.Direction.ASC,"Nombre"));
    }

    public Optional<UnidadEntity> findById(Integer Id){
        return unidadRepository.findById(Id);
    }
}
