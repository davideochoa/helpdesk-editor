package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.DAO.EstatusDAO;
import com.helpdeskeditor.application.app.data.entity.EstatusEntity;
import com.helpdeskeditor.application.app.data.repository.EstatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Component
public class EstatusFacade {
    private final EstatusRepository estatusRepository;

    public EstatusFacade(EstatusRepository estatusRepository) {
        this.estatusRepository = estatusRepository;
    }

    public List<EstatusEntity> findAll(){
        return (List<EstatusEntity>) estatusRepository.findAll();
    }

    public List<EstatusDAO> findAllDAO(Integer folio){
        return estatusRepository.findAllDAO(folio);
    }

    public List<EstatusEntity> findByFolioOrderByFecha(Integer Folio){
        return estatusRepository.findByFolioOrderByFecha(Folio);
    }

    public EstatusEntity save(EstatusEntity entity){
        return estatusRepository.save(entity);
    }

    public void deleteById(Integer Id){
        estatusRepository.deleteById(Id);
    }

    @Transactional
    public void delete(EstatusEntity entity){
        log.info("EstatusFacade:public void delete(EstatusEntity entity){");
        estatusRepository.delete(entity);
        log.info("EstatusFacade:fin");
    }

}
