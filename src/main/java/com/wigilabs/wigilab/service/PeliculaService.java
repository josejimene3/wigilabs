/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wigilabs.wigilab.service;

import com.wigilabs.wigilab.entity.Pelicula;
import com.wigilabs.wigilab.repository.PeliculaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Desarrollo
 */
@Service
public class PeliculaService {
    
    @Autowired
    private PeliculaRepository repository;
    
    public Page<Pelicula> listar(Pageable pageable){
        return repository.findAll(pageable);
    }
    
    public Pelicula listarId(Long id){
        return repository.findById(id).orElse(null);
    }
    
    public Pelicula guardar(Pelicula pelicula){
        return repository.save(pelicula);
    }
    
    public void eliminarId(Long id){
        repository.deleteById(id);
    }
}
