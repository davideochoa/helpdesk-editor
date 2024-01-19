package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.data.DAO.SolicitudDAO;
import com.helpdeskeditor.application.app.facade.SolicitudesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudesService {

    SolicitudesFacade solicitudesFacade;

    @Autowired
    public SolicitudesService(SolicitudesFacade solicitudesFacade) {
        this.solicitudesFacade = solicitudesFacade;
    }

    public List<SolicitudDAO> findAllByIdUnidad(Integer idUnidad){ return solicitudesFacade.findAllByIdUnidad(idUnidad); }
}
