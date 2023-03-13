package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.entity.BienEntity;
import com.helpdeskeditor.application.app.data.repository.BienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BienFacade {
    private BienRepository bienRepository;

    @Autowired
    public BienFacade(BienRepository bienRepository) {
        this.bienRepository = bienRepository;
    }

    @Transactional(readOnly = true)
    public List<BienEntity> findAll(){
        return bienRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<BienEntity> findByIdTipoIncidenciaOrderByNombreAsc(int idTipoIncidencia){
        return bienRepository.findByIdTipoIncidenciaOrderByNombreAsc(idTipoIncidencia);
    }

    @Transactional(readOnly = true)
    public BienEntity findByIdAndIdTipoIncidencia(Integer IdTipoIncidencia, Integer Id){
        return bienRepository.findByIdAndIdTipoIncidencia(IdTipoIncidencia,Id);
    }
    @Transactional
    public BienEntity save(BienEntity bienEntity){
        return bienRepository.save(bienEntity);
    }

}
