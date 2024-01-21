package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.DAO.SolicitudDAO;
import com.helpdeskeditor.application.app.data.entity.SolicitudEntity;
import com.helpdeskeditor.application.app.data.repository.SolicitudesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class SolicitudesFacade {
    private final SolicitudesRepository solicitudesRepository;

    @Autowired
    public SolicitudesFacade(SolicitudesRepository solicitudesRepository) {
        this.solicitudesRepository = solicitudesRepository;
    }

    @Transactional
    public SolicitudEntity save(SolicitudEntity solicitudEntity){
        return solicitudesRepository.save(solicitudEntity);
    }

    @Transactional(readOnly = true)
    public List<SolicitudDAO> findAllByIdUnidad(Integer idUnidad){ return solicitudesRepository.findAllByIdUnidad(idUnidad); }

    @Transactional(readOnly = true)
    public Page<SolicitudDAO> findAllByIdUnidadPageable(Integer idUnidad, Pageable paginacion){
        return solicitudesRepository.findAllByIdUnidadPageable(idUnidad, paginacion);
    }


    public Optional<SolicitudEntity> findById(Integer id) {
        return solicitudesRepository.findById(id);
    }
}
