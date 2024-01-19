package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.data.DAO.SolicitudDAO;
import com.helpdeskeditor.application.app.facade.SolicitudFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudesService {

    SolicitudFacade solicitudFacade;

    @Autowired
    public SolicitudesService(SolicitudFacade solicitudFacade) {
        this.solicitudFacade = solicitudFacade;
    }

    public List<SolicitudDAO> findAllByIdUnidad(Integer idUnidad){ return solicitudFacade.findAllByIdUnidad(idUnidad); }
}
