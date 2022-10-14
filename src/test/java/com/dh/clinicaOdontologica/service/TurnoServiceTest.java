package com.dh.clinicaOdontologica.service;

import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologica.model.Domicilio;
import com.dh.clinicaOdontologica.model.Odontologo;
import com.dh.clinicaOdontologica.model.Paciente;
import com.dh.clinicaOdontologica.model.Turno;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TurnoServiceTest {

    @Autowired
    private  TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @Before
    public  void cargarDatos() throws ResourceNotFoundException {
        Domicilio domicilio = new Domicilio("Estrada", "59", "Cordoba", "Cordoba");
        Paciente paciente = pacienteService.guardar(new Paciente("Maria", "Aguirre", 13212312, LocalDate.of(2022, 06, 25), domicilio));
        Odontologo odontologo = odontologoService.guardar(new Odontologo("juan", "Perez", "222"));

        turnoService.guardar(new Turno(LocalDate.of(2022, 07, 06), odontologo, paciente));

    }

    @After
    public void borrarDatos() throws BadRequestException {
        List<Turno> turnos = turnoService.buscarTodos();
        for (Turno turno : turnos) {
            turnoService.eliminar(turno.getId());
        }
    }

    @Test
    public void agregarTurnoTest() throws ResourceNotFoundException{
        Domicilio domicilio = new Domicilio("Colon", "324", "Cordoba", "Cordoba");
        Paciente paciente = pacienteService.guardar(new Paciente("Gon", "Rodriguez", 12323, LocalDate.of(2022,06,20), domicilio));
        Odontologo odontologo = odontologoService.guardar(new Odontologo("juan", "Perez", "222"));
        turnoService.guardar(new Turno(LocalDate.of(2022, 06, 06), odontologo, paciente));

        assertNotNull(paciente.getId());
    }

    @Test
    public  void traerTodosTest(){
        List<Turno> turnos = turnoService.buscarTodos();
        assertTrue(turnos.size() > 0);
    }

    @Test
    public void traerPorIdTest() throws ResourceNotFoundException {
        Turno turno = turnoService.buscarTodos().get(0);
        assertNotNull(turnoService.buscar(turno.getId()));
    }

    @Test
    public void eliminarTurnoTest() throws ResourceNotFoundException, BadRequestException {
         Turno turno = turnoService.buscarTodos().get(0);
        turnoService.eliminar(turno.getId());
        try{
            turnoService.buscar(turno.getId());
        }catch(ResourceNotFoundException resourceNotFoundException){
            Assert.assertEquals(resourceNotFoundException.getMessage(), "Turno con id " + turno.getId() + "no encontrado");
        }
    }

    @Test
    public void actalizarTurnoTest()  {
       Turno turno = turnoService.buscarTodos().get(0);
       turno.setFecha(LocalDate.of(2022, 07,27));
       assertEquals(turnoService.actualizar(turno).getFecha(), LocalDate.of(2022, 07,27));
    }

}