package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.data.entity.AreaEntity;
import com.helpdeskeditor.application.app.facade.AreasFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreasService {
    private final AreasFacade areasFacade;
    public AreasService(AreasFacade areasFacade) {
        this.areasFacade = areasFacade;
    }

    public List<AreaEntity> findAll(){
        return areasFacade.findAll();
    }

    public List<AreaEntity> findByidUnidad(int idUnidad){
        return areasFacade.findByidUnidad(idUnidad);
    }

    public AreaEntity findByIdAndIdUnidad(Integer IdUnidad,Integer IdArea){
        return areasFacade.findByIdAndIdUnidad(IdUnidad,IdArea);
    }

    public AreaEntity save(AreaEntity areaEntity){
        return areasFacade.save(areaEntity);
    }
}
