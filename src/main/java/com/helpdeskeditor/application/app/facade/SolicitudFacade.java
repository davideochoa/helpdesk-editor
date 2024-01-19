package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.DAO.SolicitudDAO;
import com.helpdeskeditor.application.app.data.entity.SolicitudEntity;
import com.helpdeskeditor.application.app.data.repository.SolicitudesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class SolicitudFacade {
    private final SolicitudesRepository solicitudesRepository;

    @Autowired
    public SolicitudFacade(SolicitudesRepository solicitudesRepository) {
        this.solicitudesRepository = solicitudesRepository;
    }

    @Transactional
    public SolicitudEntity save(SolicitudEntity solicitudEntity){
        return solicitudesRepository.save(solicitudEntity);
    }

    @Transactional(readOnly = true)
    public List<SolicitudDAO> findAllByIdUnidad(Integer idUnidad){ return solicitudesRepository.findAllByIdUnidad(idUnidad); }
}
