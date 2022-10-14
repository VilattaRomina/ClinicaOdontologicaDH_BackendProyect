package com.dh.clinicaOdontologica.controller;

import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologica.model.Odontologo;
import com.dh.clinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    OdontologoService odontologoService;

    @PostMapping()
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardar(odontologo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(odontologoService.buscar(id));
    }

    @PutMapping()
    public ResponseEntity<Odontologo> actualizarOdontologo( @RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.actualizar(odontologo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarOdontologoPorId(@PathVariable Long id) throws BadRequestException{
        odontologoService.eliminar(id);
        return ResponseEntity.ok("Se elimino el odontologo correctamente");
    }

    @GetMapping()
    public ResponseEntity<List<Odontologo>>listarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }
}
