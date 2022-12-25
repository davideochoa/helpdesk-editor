package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.data.entity.AreaEntity;
import com.helpdeskeditor.application.app.facade.AreaFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService {
    private AreaFacade areaFacade;
    public AreaService(AreaFacade areaFacade) {
        this.areaFacade = areaFacade;
    }

    public List<AreaEntity> findAll(){
        return areaFacade.findAll();
    }

    public List<AreaEntity> findByidUnidad(int idUnidad){
        return areaFacade.findByidUnidad(idUnidad);
    }
}
