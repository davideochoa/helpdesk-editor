package com.helpdeskeditor.application.app.facade;

import com.helpdeskeditor.application.app.data.DAO.FolioDAO;
import com.helpdeskeditor.application.app.data.entity.FolioEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.helpdeskeditor.application.app.data.repository.FolioRepository;

import java.util.List;
import java.util.Optional;

@Component
public class FolioFacade {
    private final FolioRepository folioRepository;

    public FolioFacade(FolioRepository folioRepository) {
        this.folioRepository = folioRepository;
    }

    @Transactional(readOnly = true)
    public List<String> getAllUsuarioReporta(){
        return  folioRepository.getAllUsuarioReporta();
    }

    @Transactional(readOnly = true)
    public List<String> getAllMarca(){
        return  folioRepository.getAllMarca();
    }

    public List<String> findMarcaByIdIncidenciaAndIdBien(Integer idIncidencia,Integer idBien){
        return  folioRepository.findMarcaByIdIncidenciaAndIdBien(idIncidencia,idBien);
    }

    @Transactional(readOnly = true)
    public List<String> getAllModelo(){
        return  folioRepository.getAllModelo();
    }

    @Transactional(readOnly = true)
    public List<String> findModeloByMarca(String marca){
        return  folioRepository.findModeloByMarca(marca);
    }

    public List<String> findModeloByIdIncidenciaAndIdBienAndMarca(Integer idIncidencia, Integer idBien,String marca){
        return  folioRepository.findModeloByIdIncidenciaAndIdBienAndMarca(idIncidencia, idBien, marca);
    }

    @Transactional(readOnly = true)
    public Optional<FolioEntity> findById(Integer Id){
        return folioRepository.findById(Id);
    }

    @Transactional
    public FolioEntity save(FolioEntity folioEntity){ return folioRepository.save(folioEntity);
    }

    @Transactional(readOnly = true)
    public List<FolioDAO> getAll(){
        return folioRepository.getAll();
    }
}
