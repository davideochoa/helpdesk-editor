package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.entity.BienEntity;
import com.helpdeskeditor.application.app.data.repository.BienRepository;
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

    public List<BienEntity> findAll(){
        return bienRepository.findAll();
    }

    public List<BienEntity> findByIdTipoIncidenciaOrderByNombreAsc(int idTipoIncidencia){
        return bienRepository.findByIdTipoIncidenciaOrderByNombreAsc(idTipoIncidencia);
    }

    public BienEntity findByIdAndIdTipoIncidencia(Integer IdTipoIncidencia, Integer Id){
        return bienRepository.findByIdAndIdTipoIncidencia(IdTipoIncidencia,Id);
    }

}
