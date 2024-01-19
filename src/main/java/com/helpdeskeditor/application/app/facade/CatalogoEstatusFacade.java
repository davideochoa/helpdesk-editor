package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.entity.CatalogoEstatusEntity;
import com.helpdeskeditor.application.app.data.repository.CatalogosEstatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class CatalogoEstatusFacade {
    private CatalogosEstatusRepository catalogosEstatusRepository;

    @Autowired
    public CatalogoEstatusFacade(CatalogosEstatusRepository catalogosEstatusRepository) {
        this.catalogosEstatusRepository = catalogosEstatusRepository;
    }

    public List<CatalogoEstatusEntity> findAll(){
        return catalogosEstatusRepository.findAll();
    }
}
