/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wigilabs.wigilab.service;

import com.wigilabs.wigilab.entity.Serie;
import com.wigilabs.wigilab.repository.SerieRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Desarrollo
 */
@Service
public class SerieService {
    
    @Autowired
    private SerieRepository repository;
    
    public Page<Serie> listar(Pageable pageable){
        return repository.findAll(pageable);
    }
    
    public Serie listarId(Long id){
        return repository.findById(id).orElse(null);
    }
    
    public Serie guardar(Serie serie){
        return repository.save(serie);
    }
    
    public void eliminarId(Long id){
        repository.deleteById(id);
    }
}
