package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.entity.FolioIncidenciaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helpdeskeditor.application.app.data.repository.FolioIncidenciaRepository;

import java.util.List;
import java.util.Optional;

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

    public Optional<FolioIncidenciaEntity> findById(Integer Id){
        return folioIncidenciaRepository.findById(Id);
    }


}
