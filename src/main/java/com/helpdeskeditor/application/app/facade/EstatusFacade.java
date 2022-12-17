package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.datos.entity.EstatusEntity;
import com.helpdeskeditor.application.app.datos.entity.UnidadEntity;
import com.helpdeskeditor.application.app.datos.repository.EstatusRepository;
import com.helpdeskeditor.application.app.datos.repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class EstatusFacade {
    private EstatusRepository estatusRepository;

    @Autowired
    public EstatusFacade(EstatusRepository estatusRepository) {
        this.estatusRepository = estatusRepository;
    }

    public List<EstatusEntity> getAllEstatus(){
        return estatusRepository.findAll();
    }
}
