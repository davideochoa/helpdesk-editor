package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.entity.BienEntity;
import com.helpdeskeditor.application.app.data.repository.BienesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BienesFacade {
    private BienesRepository bienesRepository;

    @Autowired
    public BienesFacade(BienesRepository bienesRepository) {
        this.bienesRepository = bienesRepository;
    }

    @Transactional(readOnly = true)
    public List<BienEntity> findAll(){
        return bienesRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<BienEntity> findByIdTipoIncidenciaOrderByNombreAsc(int idTipoIncidencia){
        return bienesRepository.findByIdTipoIncidenciaOrderByNombreAsc(idTipoIncidencia);
    }

    @Transactional(readOnly = true)
    public BienEntity findByIdAndIdTipoIncidencia(Integer IdTipoIncidencia, Integer Id){
        return bienesRepository.findByIdAndIdTipoIncidencia(IdTipoIncidencia,Id);
    }
    @Transactional
    public BienEntity save(BienEntity bienEntity){
        return bienesRepository.save(bienEntity);
    }

}
