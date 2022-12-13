package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.datos.entity.BiendEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helpdeskeditor.application.app.datos.repository.FolioIncidenciaRepository;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class FolioIncidenciaFacade {
    private FolioIncidenciaRepository folioIncidenciaRepository;

    @Autowired
    public FolioIncidenciaFacade(FolioIncidenciaRepository folioIncidenciaRepository) {
        this.folioIncidenciaRepository = folioIncidenciaRepository;
    }

    public List<String> getAllUsuarioReporta(){
        return  folioIncidenciaRepository.getAllUsuarioReporta();
    }

    public List<String> getAllMarca(){
        return  folioIncidenciaRepository.getAllMarca();
    }

    public List<String> getAllModelo(){
        return  folioIncidenciaRepository.getAllModelo();
    }

    public List<String> findModeloByMarca(String marca){
        return  folioIncidenciaRepository.findModeloByMarca(marca);
    }
}
