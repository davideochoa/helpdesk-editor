package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.entity.UsuarioSoporteEntity;
import com.helpdeskeditor.application.app.data.repository.UsuarioSoporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UsuarioSoporteFacade {
    private final UsuarioSoporteRepository usuarioSoporteRepository;

    @Autowired
    public UsuarioSoporteFacade(UsuarioSoporteRepository usuarioSoporteRepository) {
        this.usuarioSoporteRepository = usuarioSoporteRepository;
    }

    @Transactional(readOnly = true)
    public List<UsuarioSoporteEntity> findByOrderBynombreUsuarioAsc(){
        return usuarioSoporteRepository.findAll(Sort.by(Sort.Direction.ASC,"nombrePropio"));
    }

    @Transactional
    public UsuarioSoporteEntity save(UsuarioSoporteEntity usuarioSoporteEntity){
        return usuarioSoporteRepository.save(usuarioSoporteEntity);
    }

}
