package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.data.entity.EstatusEntity;
import com.helpdeskeditor.application.app.facade.EstatusFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstatusService {
    private EstatusFacade estatusFacade;
    public EstatusService(EstatusFacade estatusFacade) {
        this.estatusFacade = estatusFacade;
    }

    public List<EstatusEntity> findAll(){
        return estatusFacade.findAll();
    }
}
