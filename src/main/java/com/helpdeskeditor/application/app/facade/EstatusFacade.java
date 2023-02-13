package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.DAO.EstatusDAO;
import com.helpdeskeditor.application.app.data.entity.EstatusEntity;
import com.helpdeskeditor.application.app.data.repository.EstatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class EstatusFacade {
    private final EstatusRepository estatusRepository;

    @Autowired
    public EstatusFacade(EstatusRepository estatusRepository) {
        this.estatusRepository = estatusRepository;
    }

    public List<EstatusEntity> findAll(){
        return estatusRepository.findAll();
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
}
