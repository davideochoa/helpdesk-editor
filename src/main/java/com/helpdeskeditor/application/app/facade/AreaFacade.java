package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.domain.entity.AreaEntity;
import com.helpdeskeditor.application.app.domain.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class AreaFacade {
    private AreaRepository areasRepository;

    @Autowired
    public AreaFacade(AreaRepository areasRepository) {
        this.areasRepository = areasRepository;
    }

    public List<AreaEntity> getAllAreas(){
        return areasRepository.findAll();
    }
}
