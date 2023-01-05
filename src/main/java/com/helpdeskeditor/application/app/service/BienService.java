package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.data.entity.BiendEntity;
import com.helpdeskeditor.application.app.facade.BienFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BienService {
    private BienFacade bienFacade;

    public BienService(BienFacade bienFacade) {
        this.bienFacade = bienFacade;
    }

    public List<BiendEntity> findAll(){
        return bienFacade.findAll();
    }

    public List<BiendEntity> findByIdTipoIncidenciaOrderByNombreAsc(int idTipoIncidencia){
        return bienFacade.findByIdTipoIncidenciaOrderByNombreAsc(idTipoIncidencia);
    }

    public BiendEntity findByIdAndIdTipoIncidencia(Integer IdTipoIncidencia, Integer Id){
        return bienFacade.findByIdAndIdTipoIncidencia(IdTipoIncidencia,Id);
    }

}
