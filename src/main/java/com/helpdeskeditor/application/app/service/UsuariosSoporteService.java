package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.data.entity.UsuarioSoporteEntity;
import com.helpdeskeditor.application.app.facade.UsuariosSoporteFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosSoporteService {
    private final UsuariosSoporteFacade usuariosSoporteFacade;
    public UsuariosSoporteService(UsuariosSoporteFacade usuariosSoporteFacade) {
        this.usuariosSoporteFacade = usuariosSoporteFacade;
    }

    public List<UsuarioSoporteEntity> findByOrderBynombreUsuarioAsc(){
        return usuariosSoporteFacade.findByOrderBynombreUsuarioAsc();
    }

    public UsuarioSoporteEntity findByNombreUsuario(String NombreUsuarioa){
        return usuariosSoporteFacade.findByNombreUsuario(NombreUsuarioa);
    }

    public UsuarioSoporteEntity save(UsuarioSoporteEntity usuarioSoporteEntity){
        return usuariosSoporteFacade.save(usuarioSoporteEntity);
    }

    public List<UsuarioSoporteEntity> findAll(){
        return usuariosSoporteFacade.findAll();
    }

    public Optional<UsuarioSoporteEntity> findById(Integer id){
        return usuariosSoporteFacade.findById(id);
    }
}
