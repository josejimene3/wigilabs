/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wigilabs.wigilab.service;

import com.wigilabs.wigilab.entity.Episodio;
import com.wigilabs.wigilab.repository.EpisodioRepository;
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
public class EpisodioService {
    
    @Autowired
    private EpisodioRepository repository;
    
    public Page<Episodio> listar(Pageable pageable){
        return repository.findAll(pageable);
    }
    
    public Episodio listarId(Long id){
        return repository.findById(id).orElse(null);
    }
    
    public Episodio guardar(Episodio episodio){
        return repository.save(episodio);
    }
    
    public void eliminarId(Long id){
        repository.deleteById(id);
    }
}
