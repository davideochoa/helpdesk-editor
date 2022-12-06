package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.domain.entity.Area;
import com.helpdeskeditor.application.app.domain.entity.Unidad;
import com.helpdeskeditor.application.app.facade.AreasFacade;
import com.helpdeskeditor.application.app.facade.UnidadesFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreasService {
    private AreasFacade areasFacade;
    public AreasService(AreasFacade areasFacade) {
        this.areasFacade = areasFacade;
    }

    public List<Area> getAllUnidades(){
        return areasFacade.getAllUnidades();
    }
}
