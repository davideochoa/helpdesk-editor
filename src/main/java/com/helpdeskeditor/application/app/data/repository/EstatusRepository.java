package com.helpdeskeditor.application.app.data.repository;

import com.helpdeskeditor.application.app.data.entity.EstatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstatusRepository extends JpaRepository<EstatusEntity, Integer> {
    public List<EstatusEntity> findByFolioOrderByFecha(Integer Folio);
}
