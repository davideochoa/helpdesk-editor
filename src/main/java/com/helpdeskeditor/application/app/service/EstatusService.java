package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.datos.entity.EstatusEntity;
import com.helpdeskeditor.application.app.datos.entity.UnidadEntity;
import com.helpdeskeditor.application.app.facade.EstatusFacade;
import com.helpdeskeditor.application.app.facade.UnidadFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstatusService {
    private EstatusFacade estatusFacade;
    public EstatusService(EstatusFacade estatusFacade) {
        this.estatusFacade = estatusFacade;
    }

    public List<EstatusEntity> getAllEstatus(){
        return estatusFacade.getAllEstatus();
    }
}
