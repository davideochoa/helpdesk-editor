package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.data.DAO.SolicitudDAO;
import com.helpdeskeditor.application.app.data.entity.SolicitudEntity;
import com.helpdeskeditor.application.app.facade.SolicitudesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudesService {

    SolicitudesFacade solicitudesFacade;

    @Autowired
    public SolicitudesService(SolicitudesFacade solicitudesFacade) {
        this.solicitudesFacade = solicitudesFacade;
    }

    public List<SolicitudDAO> findAllByIdUnidad(Integer idUnidad){ return solicitudesFacade.findAllByIdUnidad(idUnidad); }

    public Page<SolicitudDAO> findAllByIdUnidadPageable(Integer idUnidad, Pageable paginacion){ return solicitudesFacade.findAllByIdUnidadPageable(idUnidad,paginacion); }

    public SolicitudEntity save(SolicitudEntity solicitudEntity) {
        return solicitudesFacade.save(solicitudEntity);
    }

    public Optional<SolicitudEntity> findById(Integer id) {
        return solicitudesFacade.findById(id);
    }
}
