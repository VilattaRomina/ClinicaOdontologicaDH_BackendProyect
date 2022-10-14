package com.dh.clinicaOdontologica.service.IService;

import com.dh.clinicaOdontologica.exceptions.BadRequestException;
import com.dh.clinicaOdontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IService<T>{
    public T guardar(T object) throws BadRequestException, ResourceNotFoundException;

    public T buscar(Long id) throws BadRequestException, ResourceNotFoundException;

    public List<T> buscarTodos();

    public T actualizar(T object);

    public void eliminar(Long id) throws BadRequestException;


}
