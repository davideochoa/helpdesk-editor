package com.helpdeskeditor.application.app.service;

import com.helpdeskeditor.application.app.data.entity.UsuarioSoporteEntity;
import com.helpdeskeditor.application.app.facade.UsuarioSoporteFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioSoporteService {
    private final UsuarioSoporteFacade usuarioSoporteFacade;
    public UsuarioSoporteService(UsuarioSoporteFacade usuarioSoporteFacade) {
        this.usuarioSoporteFacade = usuarioSoporteFacade;
    }

    public List<UsuarioSoporteEntity> findByOrderBynombreUsuarioAsc(){
        return usuarioSoporteFacade.findByOrderBynombreUsuarioAsc();
    }

    public UsuarioSoporteEntity findByNombreUsuario(String NombreUsuarioa){
        return usuarioSoporteFacade.findByNombreUsuario(NombreUsuarioa);
    }

    public UsuarioSoporteEntity save(UsuarioSoporteEntity usuarioSoporteEntity){
        return usuarioSoporteFacade.save(usuarioSoporteEntity);
    }


}
