package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.domain.entity.Unidad;
import com.helpdeskeditor.application.app.facade.UnidadesFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadesService {
    private UnidadesFacade unidadesFacade;
    public UnidadesService(UnidadesFacade unidadesFacade) {
        this.unidadesFacade = unidadesFacade;
    }

    public List<Unidad> getAllUnidades(){
        return unidadesFacade.getAllUnidades();
    }
}
