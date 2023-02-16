package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.data.DAO.EstatusDAO;
import com.helpdeskeditor.application.app.data.entity.EstatusEntity;
import com.helpdeskeditor.application.app.facade.EstatusFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class EstatusService {
    private final EstatusFacade estatusFacade;
    public EstatusService(EstatusFacade estatusFacade) {
        this.estatusFacade = estatusFacade;
    }

    public List<EstatusEntity> findAll(){
        return estatusFacade.findAll();
    }

    public List<EstatusDAO> findAllDAO(Integer folio){
        return estatusFacade.findAllDAO(folio);
    }

    public List<EstatusEntity> findByFolioOrderByFecha(Integer Folio){
        return estatusFacade.findByFolioOrderByFecha(Folio);
    }

    public EstatusEntity save(EstatusEntity entity){
        return estatusFacade.save(entity);
    }
    public void deleteById(Integer Id){
        estatusFacade.deleteById(Id);
    }
    public void delete(EstatusEntity entity){
        log.info("EstatusService:public void delete(EstatusEntity entity)");
        estatusFacade.delete(entity);
    }

}
