/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wigilabs.wigilab.controller;

import com.google.gson.Gson;
import com.wigilabs.wigilab.entity.Pelicula;
import com.wigilabs.wigilab.service.PeliculaService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Desarrollo
 */
@RestController
@RequestMapping("/api/v1")
public class PeliculaController {

    private Gson gson = new Gson();
    
    @Autowired
    private PeliculaService service;

    @GetMapping("/peliculas")
    public ResponseEntity<?> listar(Pageable pageable) {
        Page<Pelicula> pages = service.listar(pageable);
        
        Map<String, Object> page = new HashMap<>();
        page.put("currentPage", pages.getNumber());
        page.put("totalPages", pages.getTotalPages());
        page.put("totalItems", pages.getTotalElements());
        page.put("size", pages.getSize());
        page.put("peliculas", pages.getContent());
        
        Map<String, Object> response = new HashMap<>();
        response.put("error", "0");
        response.put("response", page);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/peliculas/{id}")
    public ResponseEntity<?> listarId(@PathVariable(value = "id") Long id) {
        Pelicula pelicula = null;
        Map<String, Object> response = null;
        if (id > 0) {
            pelicula = service.listarId(id);
            if (pelicula == null) {
                response = new HashMap<>();
                response.put("error", "1");
                response.put("response", "La pel??cula no existe");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } else {
            response = new HashMap<>();
            response.put("error", "1");
            response.put("response", "El id de la pel??cula no existe");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response = new HashMap<>();
        response.put("error", "0");
        response.put("response", pelicula);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/peliculas")
    public ResponseEntity<?> guardar(@RequestBody Pelicula pelicula) {
        Pelicula obj = service.guardar(pelicula);
        Map<String, Object> response = new HashMap<>();
        response.put("error", "0");
        response.put("response", obj);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/peliculas")
    public ResponseEntity<?> editar(@RequestBody Pelicula pelicula) {        
        Pelicula obj = service.guardar(pelicula);
        Map<String, Object> response = new HashMap<>();
        response.put("error", "0");
        response.put("response", obj);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/peliculas/{id}")
    public ResponseEntity<?> eliminar(@PathVariable(value = "id") Long id) {
        Pelicula pelicula = null;
        Map<String, Object> response = null;
        if (id > 0) {
            pelicula = service.listarId(id);
            if (pelicula == null) {
                response = new HashMap<>();
                response.put("error", "1");
                response.put("mensaje", "La pel??cula no existe");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } else {
            response = new HashMap<>();
            response.put("error", "1");
            response.put("mensaje", "El id de la pel??cula no existe");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        service.eliminarId(id);
        response = new HashMap<>();
        response.put("error", "0");
        response.put("response", pelicula);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
