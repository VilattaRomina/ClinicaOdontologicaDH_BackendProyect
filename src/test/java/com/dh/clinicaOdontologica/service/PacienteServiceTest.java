package com.dh.clinicaOdontologica.service;

import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologica.model.Domicilio;
import com.dh.clinicaOdontologica.model.Paciente;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest

 public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Before
    public void cargarDatos(){
        Domicilio domicilio = new Domicilio("Estrada", "59", "Cordoba", "Cordoba");
        Paciente paciente = pacienteService.guardar(new Paciente("Maria", "Aguirre", 13212312, LocalDate.of(2022, 06, 25), domicilio));
    }

   @After
    public void borrarDatos() throws BadRequestException {
        List<Paciente> pacientes = pacienteService.buscarTodos();
        for (Paciente paciente : pacientes) {
            pacienteService.eliminar(paciente.getId());
        }
    }


    @Test
    public void agregarPacienteTest(){
        Domicilio domicilio = new Domicilio("Colon", "324", "Cordoba", "Cordoba");
       Paciente paciente = pacienteService.guardar(new Paciente("Gon", "Rodriguez", 12323, LocalDate.of(2022,06,20), domicilio));
       assertNotNull(paciente.getId());
    }

    @Test
    public  void traerTodosTest(){
        List<Paciente> pacientes = pacienteService.buscarTodos();
        assertTrue(pacientes.size() > 0);

    }

    @Test
    public void traerPorIdTest() throws ResourceNotFoundException {
       Paciente paciente = pacienteService.buscarTodos().get(0);
        assertNotNull(pacienteService.buscar(paciente.getId()));
    }

    @Test
    public void eliminarPacienteTest() throws ResourceNotFoundException, BadRequestException {
        Paciente paciente = pacienteService.buscarTodos().get(0);
        pacienteService.eliminar(paciente.getId());
        try{
            pacienteService.buscar(paciente.getId());
        }catch(ResourceNotFoundException resourceNotFoundException){
            Assert.assertEquals(resourceNotFoundException.getMessage(), "No se encontro un paciente con id " + paciente.getId());
        }
    }


    @Test
    public void actualizarPacienteTest() throws ResourceNotFoundException {
        Paciente paciente = pacienteService.buscarTodos().get(0);
        paciente.setNombre("Diana");
        pacienteService.actualizar(paciente);
        assertTrue(pacienteService.buscar(paciente.getId()).getNombre() == "Diana");
    }



}