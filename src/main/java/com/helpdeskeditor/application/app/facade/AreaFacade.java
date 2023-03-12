package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.entity.AreaEntity;
import com.helpdeskeditor.application.app.data.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AreaFacade {
    private final AreaRepository areaRepository;

    @Autowired
    public AreaFacade(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @Transactional(readOnly = true)
    public List<AreaEntity> findAll(){
        return areaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<AreaEntity> findByidUnidad(int idUnidad){
        return areaRepository.findByIdUnidadOrderByNombre(idUnidad);
    }

    @Transactional(readOnly = true)
    public AreaEntity findByIdAndIdUnidad(int IdUnidad, int Id){
        return areaRepository.findByIdAndIdUnidad(IdUnidad,Id);
    }
    @Transactional
    public AreaEntity save(AreaEntity areaEntity){
        return areaRepository.save(areaEntity);
    }

}
