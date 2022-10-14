package com.dh.clinicaOdontologica.controller;
import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologica.model.Turno;
import com.dh.clinicaOdontologica.repository.OdontologoRepository;
import com.dh.clinicaOdontologica.repository.PacienteRepository;
import com.dh.clinicaOdontologica.service.OdontologoService;
import com.dh.clinicaOdontologica.service.PacienteService;
import com.dh.clinicaOdontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    TurnoService turnoService;
    @Autowired
    PacienteService pacienteService;
    @Autowired
    OdontologoService odontologoService;

    @PostMapping()
    public ResponseEntity<String> guardarTurno(@RequestBody Turno t) throws ResourceNotFoundException {
        ResponseEntity<String> respuesta = null;
        if(turnoService.guardar(t) != null){
            respuesta = ResponseEntity.ok("El turno fue registrado con éxito");
        }else{
            respuesta = ResponseEntity.internalServerError().body("Ooops");
        }
        return respuesta;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurnoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(turnoService.buscar(id));
    }

    @PutMapping()
    public ResponseEntity<Turno> actualizarTurno( @RequestBody Turno turno){
        return ResponseEntity.ok(turnoService.actualizar(turno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTurnoPorId(@PathVariable Long id) throws BadRequestException {
        turnoService.eliminar(id);
        return ResponseEntity.ok("Se elimino el turno correctamente");
    }

    @GetMapping
    public ResponseEntity<List<Turno>>listarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }


}
