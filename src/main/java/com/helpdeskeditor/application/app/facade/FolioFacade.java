package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.entity.FolioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helpdeskeditor.application.app.data.repository.FolioRepository;

import java.util.List;
import java.util.Optional;

@Component
@Transactional(readOnly = true)
public class FolioFacade {
    private FolioRepository folioRepository;

    @Autowired
    public FolioFacade(FolioRepository folioRepository) {
        this.folioRepository = folioRepository;
    }

    public List<String> getAllUsuarioReporta(){
        return  folioRepository.getAllUsuarioReporta();
    }

    public List<String> getAllMarca(){
        return  folioRepository.getAllMarca();
    }

    public List<String> getAllModelo(){
        return  folioRepository.getAllModelo();
    }

    public List<String> findModeloByMarca(String marca){
        return  folioRepository.findModeloByMarca(marca);
    }

    public Optional<FolioEntity> findById(Integer Id){
        return folioRepository.findById(Id);
    }

    public FolioEntity save(FolioEntity folioEntity){ return folioRepository.save(folioEntity);}
}
