package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.entity.AreaEntity;
import com.helpdeskeditor.application.app.data.repository.AreasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AreaFacade {
    private final AreasRepository areasRepository;

    @Autowired
    public AreaFacade(AreasRepository areasRepository) {
        this.areasRepository = areasRepository;
    }

    @Transactional(readOnly = true)
    public List<AreaEntity> findAll(){
        return areasRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<AreaEntity> findByidUnidad(int idUnidad){
        return areasRepository.findByIdUnidadOrderByNombre(idUnidad);
    }

    @Transactional(readOnly = true)
    public AreaEntity findByIdAndIdUnidad(int IdUnidad, int Id){
        return areasRepository.findByIdAndIdUnidad(IdUnidad,Id);
    }
    @Transactional
    public AreaEntity save(AreaEntity areaEntity){
        return areasRepository.save(areaEntity);
    }

}
