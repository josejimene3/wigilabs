/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wigilabs.wigilab.service;

import com.wigilabs.wigilab.entity.Temporada;
import com.wigilabs.wigilab.repository.TemporadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Desarrollo
 */
@Service
public class TemporadaService {
    
    @Autowired
    private TemporadaRepository repository;
    
    public Page<Temporada> listar(Pageable pageable){
        return repository.findAll(pageable);
    }
    
    public Temporada listarId(Long id){
        return repository.findById(id).orElse(null);
    }
    
    public Temporada guardar(Temporada temporada){
        return repository.save(temporada);
    }
    
    public void eliminarId(Long id){
        repository.deleteById(id);
    }
}
