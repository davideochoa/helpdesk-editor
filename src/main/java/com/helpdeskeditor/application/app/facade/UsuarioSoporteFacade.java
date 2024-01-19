package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.entity.UsuarioSoporteEntity;
import com.helpdeskeditor.application.app.data.repository.UsuariosSoporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class UsuarioSoporteFacade {
    private final UsuariosSoporteRepository usuariosSoporteRepository;

    @Autowired
    public UsuarioSoporteFacade(UsuariosSoporteRepository usuariosSoporteRepository) {
        this.usuariosSoporteRepository = usuariosSoporteRepository;
    }

    @Transactional(readOnly = true)
    public List<UsuarioSoporteEntity> findByOrderBynombreUsuarioAsc(){
        return usuariosSoporteRepository.findAll(Sort.by(Sort.Direction.ASC,"nombrePropio"));
    }

    @Transactional(readOnly = true)
    public UsuarioSoporteEntity findByNombreUsuario(String NombreUsuarioa){
        return usuariosSoporteRepository.findByNombreUsuario(NombreUsuarioa);
    }

    @Transactional
    public UsuarioSoporteEntity save(UsuarioSoporteEntity usuarioSoporteEntity){
        return usuariosSoporteRepository.save(usuarioSoporteEntity);
    }

    @Transactional(readOnly = true)
    public List<UsuarioSoporteEntity> findAll(){
        return usuariosSoporteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<UsuarioSoporteEntity> findById(Integer id){
        return usuariosSoporteRepository.findById(id);
    }
}
