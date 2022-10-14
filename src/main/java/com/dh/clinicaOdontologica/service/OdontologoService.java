package com.dh.clinicaOdontologica.service;

import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologica.model.Odontologo;
import com.dh.clinicaOdontologica.repository.OdontologoRepository;
import com.dh.clinicaOdontologica.service.IService.IService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OdontologoService implements IService<Odontologo> {
    @Autowired
    OdontologoRepository odontologoRepository;

    final static Logger logger = Logger.getLogger(OdontologoService.class);


    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.debug("Guardando odontologo");
        odontologoRepository.save(odontologo);
        logger.debug("Odontologo guardado exitosamente");
        return odontologo;
    }

    @Override
    public Odontologo buscar(Long id) throws ResourceNotFoundException {
        logger.debug("Buscando odontologo");
        if(odontologoRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("No se encontro un odontologo con id " + id);
        }else {
            logger.debug("Se encontro el odontologo con id " +  id);
           return odontologoRepository.findById(id).get();
        }

    }

    @Override
    public List<Odontologo> buscarTodos() {
        logger.debug("Buscando odontologos");
        return odontologoRepository.findAll();
    }

    @Override
    public Odontologo actualizar( Odontologo odontologo) {
        logger.debug("Modificando odontologo");
        odontologoRepository.save(odontologo);
        return odontologo;
    }

    @Override
    public void eliminar(Long id) throws BadRequestException{
        logger.debug("Buscando odontologo");
        if (odontologoRepository.findById(id).isEmpty()){
            throw new BadRequestException("No existe ningún odontologo con id " + id);
        }else{
            logger.debug("Se elimino el odontologo con id " + id);
            odontologoRepository.deleteById(id);
        }
    }




}
