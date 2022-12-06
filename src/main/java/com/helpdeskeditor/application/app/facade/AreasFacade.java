package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.domain.entity.Area;
import com.helpdeskeditor.application.app.domain.entity.Unidad;
import com.helpdeskeditor.application.app.domain.repository.AreasRepository;
import com.helpdeskeditor.application.app.domain.repository.UnidadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class AreasFacade {
    private AreasRepository areasRepository;

    @Autowired
    public AreasFacade(AreasRepository areasRepository) {
        this.areasRepository = areasRepository;
    }

    public List<Area> getAllUnidades(){
        return areasRepository.findAll();
    }
}
