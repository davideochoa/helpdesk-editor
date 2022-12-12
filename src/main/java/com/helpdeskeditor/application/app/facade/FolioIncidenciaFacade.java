package com.helpdeskeditor.application.app.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helpdeskeditor.application.app.domain.repository.FolioIncidenciaRepository;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class FolioIncidenciaFacade {
    private FolioIncidenciaRepository folioIncidenciaRepository;

    @Autowired
    public FolioIncidenciaFacade(FolioIncidenciaRepository folioIncidenciaRepository) {
        this.folioIncidenciaRepository = folioIncidenciaRepository;
    }

    public List<String> getUsuarioReporta(){
        return  folioIncidenciaRepository.getUsuarioReporta();
    }

}
