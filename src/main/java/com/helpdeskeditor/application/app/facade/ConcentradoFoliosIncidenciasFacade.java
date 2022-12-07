package com.helpdeskeditor.application.app.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helpdeskeditor.application.app.domain.repository.ConcentradoFoliosIncidenciasRepository;
import com.helpdeskeditor.application.app.domain.entity.ConcentradoFolioIncidencia;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class ConcentradoFoliosIncidenciasFacade {
    private ConcentradoFoliosIncidenciasRepository concentradoFoliosIncidenciasRepository;

    @Autowired
    public ConcentradoFoliosIncidenciasFacade(ConcentradoFoliosIncidenciasRepository concentradoFoliosIncidenciasRepository) {
        this.concentradoFoliosIncidenciasRepository = concentradoFoliosIncidenciasRepository;
    }

    public List<ConcentradoFolioIncidencia> findusuarioReporta(){
        return  concentradoFoliosIncidenciasRepository.findByUsuarioReporta();
    }
}
