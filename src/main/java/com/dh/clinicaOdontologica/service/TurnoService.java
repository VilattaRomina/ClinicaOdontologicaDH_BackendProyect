package com.dh.clinicaOdontologica.service;

import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologica.model.Turno;
import com.dh.clinicaOdontologica.repository.TurnoRepository;
import com.dh.clinicaOdontologica.service.IService.IService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class TurnoService implements IService<Turno> {
    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    private  static  final Logger logger = Logger.getLogger(TurnoService.class);

    @Override
    public Turno guardar(Turno turno) throws  ResourceNotFoundException {
        logger.debug("Buscando paciente y odontologo");
        if (pacienteService.buscar(turno.getPaciente().getId()) == null || odontologoService.buscar(turno.getOdontologo().getId()) == null)
            throw new ResourceNotFoundException("El paciente o el odontologo no existen");
        logger.debug("Turno guardado exitosamente");
        return  turnoRepository.save(turno);
    }

    @Override
    public Turno buscar(Long id) throws ResourceNotFoundException {
            logger.info("Buscando turno con id: " + id);
        if(turnoRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Turno con id " + id + "no encontrado");
        }else {
            logger.debug("Se encontro el turno con id " +  id);
            return turnoRepository.findById(id).get();
        }

    }

    @Override
    public List<Turno> buscarTodos() {
        logger.debug("Buscando turnos");
        return turnoRepository.findAll();
    }

    @Override
    public Turno actualizar( Turno turno) {
        logger.debug("Modificando odontologo");
        turnoRepository.save(turno);
        return turno;
    }

    @Override
    public void eliminar(Long id) throws BadRequestException {
        logger.debug("Buscando turno");
        if (turnoRepository.findById(id).isEmpty()){
            throw new BadRequestException("No existe turno con id " + id);
        }else{
            logger.debug("Se elimino el turno con id " + id);
            turnoRepository.deleteById(id);
        }
    }




}
