package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.domain.entity.AreaEntity;
import com.helpdeskeditor.application.app.facade.AreaFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService {
    private AreaFacade areaFacade;
    public AreaService(AreaFacade areaFacade) {
        this.areaFacade = areaFacade;
    }

    public List<AreaEntity> getAllAreas(){
        return areaFacade.getAllAreas();
    }
}
