package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.domain.repository.UnidadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class UnidadesFacade {
    private UnidadesRepository unidadesRepository;

    @Autowired
    public UnidadesFacade(UnidadesRepository unidadesRepository) {
        this.unidadesRepository = unidadesRepository;
    }
}
