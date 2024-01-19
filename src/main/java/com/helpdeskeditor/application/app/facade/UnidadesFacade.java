package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.entity.UnidadEntity;
import com.helpdeskeditor.application.app.data.repository.UnidadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class UnidadesFacade {
    private final UnidadesRepository unidadesRepository;

    @Autowired
    public UnidadesFacade(UnidadesRepository unidadesRepository) {
        this.unidadesRepository = unidadesRepository;
    }

    @Transactional(readOnly = true)
    public List<UnidadEntity> findAll(){
        return unidadesRepository.findAll(Sort.by(Sort.Direction.ASC,"Nombre"));
    }

    @Transactional(readOnly = true)
    public Optional<UnidadEntity> findById(Integer Id){
        return unidadesRepository.findById(Id);
    }

    @Transactional
    public UnidadEntity save(UnidadEntity unidadEntity){
        return unidadesRepository.save(unidadEntity);
    }
}
