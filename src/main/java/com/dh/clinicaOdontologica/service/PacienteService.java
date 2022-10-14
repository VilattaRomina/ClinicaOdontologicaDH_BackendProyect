package com.dh.clinicaOdontologica.service;

import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologica.model.Paciente;
import com.dh.clinicaOdontologica.repository.DomicilioRepository;
import com.dh.clinicaOdontologica.repository.PacienteRepository;
import com.dh.clinicaOdontologica.service.IService.IService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PacienteService implements IService<Paciente> {
    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    DomicilioRepository domicilioRepository;

    final static Logger logger = Logger.getLogger(OdontologoService.class);


    @Override
    public Paciente guardar(Paciente paciente) {
        logger.debug("Guardando paciente");
        pacienteRepository.save(paciente);
        logger.debug("Paciente guardado exitosamente");
        return paciente;
    }

    @Override
    public Paciente buscar(Long id) throws ResourceNotFoundException {
        logger.debug("Buscando paciente");
        if (pacienteRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("No se encontro un paciente con id " + id);
        }else {
            logger.debug("Se encontro el paciente con id " +  id);
            return pacienteRepository.findById(id).get();
        }
    }

    @Override
    public List<Paciente> buscarTodos() {
        logger.debug("Buscando pacientes");
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente actualizar( Paciente paciente) {
        logger.debug("Modificando paciente");
        pacienteRepository.save(paciente);
        return paciente;
    }

    @Override
    public void eliminar(Long id) throws BadRequestException {
        logger.debug("Buscando paciente");
        if (pacienteRepository.findById(id).isEmpty()){
            throw new BadRequestException("No existe ningún odontologo con id " + id);
        }else{
            logger.debug("Se elimino el paciente con id " + id);
            pacienteRepository.deleteById(id);
        }
    }


}
