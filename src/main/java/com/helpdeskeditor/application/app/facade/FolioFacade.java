package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.entity.FolioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helpdeskeditor.application.app.data.repository.FolioRepository;

import java.util.List;
import java.util.Optional;

@Component
public class FolioFacade {
    private FolioRepository folioRepository;

    @Autowired
    public FolioFacade(FolioRepository folioRepository) {
        this.folioRepository = folioRepository;
    }

    @Transactional(readOnly = true)
    public List<String> getAllUsuarioReporta(){
        return  folioRepository.getAllUsuarioReporta();
    }

    @Transactional(readOnly = true)
    public List<String> getAllMarca(){
        return  folioRepository.getAllMarca();
    }

    @Transactional(readOnly = true)
    public List<String> getAllModelo(){
        return  folioRepository.getAllModelo();
    }

    @Transactional(readOnly = true)
    public List<String> findModeloByMarca(String marca){
        return  folioRepository.findModeloByMarca(marca);
    }

    @Transactional(readOnly = true)
    public Optional<FolioEntity> findById(Integer Id){
        return folioRepository.findById(Id);
    }

    @Transactional
    @Modifying
    public FolioEntity save(FolioEntity folioEntity){
        FolioEntity folioEntity_update = folioRepository.findById(folioEntity.getId()).get();

        folioEntity_update.setIdUnidad(folioEntity.getIdUnidad());
        folioEntity_update.setIdArea(folioEntity.getIdArea());
        return folioRepository.save(folioEntity_update);
    }
}
