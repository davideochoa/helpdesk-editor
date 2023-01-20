package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.data.entity.FolioEntity;
import com.helpdeskeditor.application.app.facade.FolioFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FolioService {

    FolioFacade folioFacade;

    @Autowired
    public FolioService(FolioFacade folioFacade) {
        this.folioFacade = folioFacade;
    }

    public List<String> getAllUsuarioReporta(){
        return folioFacade.getAllUsuarioReporta();
    }

    public List<String> getAllMarca(){
        return folioFacade.getAllMarca();
    }

    public List<String> getAllModelo(){
        return folioFacade.getAllModelo();
    }

    public List<String> findModeloByMarca(String marca){
        return  folioFacade.findModeloByMarca(marca);
    }

    public Optional<FolioEntity> findById(Integer Id){
        return folioFacade.findById(Id);
    }

    public FolioEntity save(FolioEntity folioEntity){ return folioFacade.save(folioEntity);}


}
