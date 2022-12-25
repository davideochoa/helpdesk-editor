package com.helpdeskeditor.application.app.data.repository;

import com.helpdeskeditor.application.app.data.entity.EstatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstatusRepository extends JpaRepository<EstatusEntity, Integer> {

}
