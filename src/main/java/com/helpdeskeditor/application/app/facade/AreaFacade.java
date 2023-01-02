package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.entity.AreaEntity;
import com.helpdeskeditor.application.app.data.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class AreaFacade {
    private AreaRepository areasRepository;

    @Autowired
    public AreaFacade(AreaRepository areasRepository) {
        this.areasRepository = areasRepository;
    }

    public List<AreaEntity> findAll(){
        return areasRepository.findAll();
    }

    public List<AreaEntity> findByidUnidad(int idUnidad){
        return areasRepository.findByIdUnidadOrderByNombre(idUnidad);
    }

    public AreaEntity findByIdAndIdUnidad(int IdUnidad, int Id){
        return areasRepository.findByIdAndIdUnidad(IdUnidad,Id);
    }

}
