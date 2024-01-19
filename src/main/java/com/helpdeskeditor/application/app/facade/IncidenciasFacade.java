package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.entity.IncidenciaEntity;
import com.helpdeskeditor.application.app.data.repository.IncidenciasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional(readOnly = true)
public class IncidenciasFacade {
    private IncidenciasRepository incidenciasRepository;

    @Autowired
    public IncidenciasFacade(IncidenciasRepository incidenciasRepository) {
        this.incidenciasRepository = incidenciasRepository;
    }

    @Transactional(readOnly = true)
    public List<IncidenciaEntity> findAll(){
        return incidenciasRepository.findAll(Sort.by(Sort.Direction.ASC,"Nombre"));
    }

    @Transactional(readOnly = true)
    public Optional<IncidenciaEntity> findById(Integer Id){
        return incidenciasRepository.findById(Id);
    }

    @Transactional
    public IncidenciaEntity save(IncidenciaEntity incidenciaEntity){
        return incidenciasRepository.save(incidenciaEntity);
    }
}
