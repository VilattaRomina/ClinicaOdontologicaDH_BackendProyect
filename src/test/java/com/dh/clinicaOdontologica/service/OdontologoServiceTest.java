package com.dh.clinicaOdontologica.service;

import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.clinicaOdontologica.model.Odontologo;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest


 public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Before
    public void cargarDatos(){
        odontologoService.guardar(new Odontologo("juan", "Perez", "222"));
    }

    @After
    public void borrarDatos() throws BadRequestException {
        List<Odontologo> odontologos = odontologoService.buscarTodos();
        for (Odontologo odontologo : odontologos) {
            odontologoService.eliminar(odontologo.getId());
        }

    }

    @Test
    public void agregarOdontologo() {
        Odontologo odontologo = odontologoService.guardar(new Odontologo("Juan", "Ramirez", "333"));
        Assert.assertTrue(odontologo.getId() != null);

    }

    @Test
    public void traerTodos() {
        List<Odontologo> odontologos = odontologoService.buscarTodos();
        Assert.assertTrue(!odontologos.isEmpty());
        Assert.assertTrue(odontologos.size() > 0);
        System.out.println(odontologos);
    }

    @Test
    public void traerPorId() throws ResourceNotFoundException {
        Odontologo odontologo = odontologoService.buscarTodos().get(0);
        Assert.assertNotNull(odontologoService.buscar(odontologo.getId()));
    }

     @Test
   public void eliminarOdontologoTest() throws ResourceNotFoundException, BadRequestException {
       Odontologo odontologo = odontologoService.buscarTodos().get(0);
       odontologoService.eliminar(odontologo.getId());
       try{
           odontologoService.buscar(odontologo.getId());
           fail("Se esperaba una excepcion");
       }catch (ResourceNotFoundException resourceNotFoundException){
        Assert.assertEquals(resourceNotFoundException.getMessage(), "No se encontro un odontologo con id " + odontologo.getId());
       }
   }


   @Test
    public void actualizarOdontologoTest() throws ResourceNotFoundException {
        Odontologo odontologo = odontologoService.buscarTodos().get(0);
        odontologo.setNombre("Romi");
        odontologoService.actualizar( odontologo);
        Assert.assertTrue(odontologoService.buscar(odontologo.getId()).getNombre() == "Romi");

        }



}