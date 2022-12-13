package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.datos.entity.BiendEntity;
import com.helpdeskeditor.application.app.datos.repository.BienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class BienFacade {
    private BienRepository bienRepository;

    @Autowired
    public BienFacade(BienRepository bienRepository) {
        this.bienRepository = bienRepository;
    }

    public List<BiendEntity> getAllBienes(){
        return bienRepository.findAll();
    }

    public List<BiendEntity> findByidTipoIncidencia(int idTipoIncidencia){
        return bienRepository.findByidTipoIncidencia(idTipoIncidencia);
    }
}
