package com.dh.clinicaOdontologica.controller;

import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologica.model.Paciente;
import com.dh.clinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    PacienteService pacienteService;

    @PostMapping()
    public ResponseEntity<String> guardarPaciente(@RequestBody Paciente paciente){
        ResponseEntity<String> respuesta = null;

        if(pacienteService.guardar(paciente) != null){
            respuesta = ResponseEntity.ok("El paciente fue registrado con éxito");
        }else{
            respuesta = ResponseEntity.internalServerError().body("Ooops");
        }
        return respuesta;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(pacienteService.buscar(id));
    }

    @PutMapping()
    public ResponseEntity<Paciente> actualizarPaciente( @RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.actualizar(paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarPacientePorId(@PathVariable Long id) throws BadRequestException {
        pacienteService.eliminar(id);
        return  ResponseEntity.ok("Se elimino el paciente correctamente");
    }

    @GetMapping
    public ResponseEntity<List<Paciente>>listarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

}
