package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.data.entity.BienEntity;
import com.helpdeskeditor.application.app.facade.BienesFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BienesService {
    private BienesFacade bienesFacade;

    public BienesService(BienesFacade bienesFacade) {
        this.bienesFacade = bienesFacade;
    }

    public List<BienEntity> findAll(){
        return bienesFacade.findAll();
    }

    public List<BienEntity> findByIdTipoIncidenciaOrderByNombreAsc(int idTipoIncidencia){
        return bienesFacade.findByIdTipoIncidenciaOrderByNombreAsc(idTipoIncidencia);
    }

    public BienEntity findByIdAndIdTipoIncidencia(Integer IdTipoIncidencia, Integer Id){
        return bienesFacade.findByIdAndIdTipoIncidencia(IdTipoIncidencia,Id);
    }

    public BienEntity save(BienEntity bienEntity){
        return bienesFacade.save(bienEntity);
    }

}
