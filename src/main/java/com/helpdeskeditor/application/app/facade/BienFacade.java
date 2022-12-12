package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.domain.entity.BiendEntity;
import com.helpdeskeditor.application.app.domain.entity.UnidadEntity;
import com.helpdeskeditor.application.app.domain.repository.BienRepository;
import com.helpdeskeditor.application.app.domain.repository.UnidadRepository;
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
}
