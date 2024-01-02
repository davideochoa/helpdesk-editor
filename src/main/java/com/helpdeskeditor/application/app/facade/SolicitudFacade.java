package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.entity.AreaEntity;
import com.helpdeskeditor.application.app.data.entity.SolicitudEntity;
import com.helpdeskeditor.application.app.data.repository.AreaRepository;
import com.helpdeskeditor.application.app.data.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class SolicitudFacade {
    private final SolicitudRepository solicitudRepository;

    @Autowired
    public SolicitudFacade(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }

    @Transactional
    public SolicitudEntity save(SolicitudEntity solicitudEntity){
        return solicitudRepository.save(solicitudEntity);
    }

}
