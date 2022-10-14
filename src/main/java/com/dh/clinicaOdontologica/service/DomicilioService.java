package com.dh.clinicaOdontologica.service;

import com.dh.clinicaOdontologica.model.Domicilio;
import com.dh.clinicaOdontologica.repository.DomicilioRepository;
import com.dh.clinicaOdontologica.service.IService.IService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService implements IService<Domicilio> {


    @Autowired
    DomicilioRepository domicilioRepository;

    final static Logger logger = Logger.getLogger(OdontologoService.class);


    @Override
    public Domicilio guardar(Domicilio domicilio) {
        domicilioRepository.save(domicilio);
        logger.info("Domicilio guardado exitosamente");
        return domicilio;
    }


    @Override
    public Domicilio buscar(Long id) {
        Domicilio domicilio = null;
        if(domicilioRepository.findById(id).isPresent()){
            domicilio = domicilioRepository.findById(id).get();
        }
        return domicilio;
    }


    @Override
    public List<Domicilio> buscarTodos() {
        return domicilioRepository.findAll();
    }

    @Override
    public Domicilio actualizar( Domicilio domicilio) {
        return null;
    }

    @Override
    public void eliminar(Long id) {
       if(domicilioRepository.findById(id).isPresent()){
           domicilioRepository.deleteById(id);
       }
    }
}
