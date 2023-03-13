package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.data.entity.BienEntity;
import com.helpdeskeditor.application.app.facade.BienFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BienService {
    private BienFacade bienFacade;

    public BienService(BienFacade bienFacade) {
        this.bienFacade = bienFacade;
    }

    public List<BienEntity> findAll(){
        return bienFacade.findAll();
    }

    public List<BienEntity> findByIdTipoIncidenciaOrderByNombreAsc(int idTipoIncidencia){
        return bienFacade.findByIdTipoIncidenciaOrderByNombreAsc(idTipoIncidencia);
    }

    public BienEntity findByIdAndIdTipoIncidencia(Integer IdTipoIncidencia, Integer Id){
        return bienFacade.findByIdAndIdTipoIncidencia(IdTipoIncidencia,Id);
    }

    public BienEntity save(BienEntity bienEntity){
        return bienFacade.save(bienEntity);
    }

}
