/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wigilabs.wigilab.repository;

import com.wigilabs.wigilab.entity.Episodio;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Desarrollo
 */
public interface EpisodioRepository extends JpaRepository<Episodio, Long> {
    
}
