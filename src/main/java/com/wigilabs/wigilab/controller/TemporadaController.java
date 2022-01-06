/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wigilabs.wigilab.controller;

import com.wigilabs.wigilab.entity.Temporada;
import com.wigilabs.wigilab.service.TemporadaService;
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
public class TemporadaController {

    @Autowired
    private TemporadaService service;

    @GetMapping("/temporadas")
    public ResponseEntity<?> listar(Pageable pageable) {
        Page<Temporada> pages = service.listar(pageable);

        Map<String, Object> page = new HashMap<>();
        page.put("currentPage", pages.getNumber());
        page.put("totalPages", pages.getTotalPages());
        page.put("totalItems", pages.getTotalElements());
        page.put("size", pages.getSize());
        page.put("temporadas", pages.getContent());

        Map<String, Object> response = new HashMap<>();
        response.put("error", "0");
        response.put("response", page);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/temporadas/{id}")
    public ResponseEntity<?> listarId(@PathVariable(value = "id") Long id) {
        Temporada temporada = null;
        Map<String, Object> response = null;
        if (id > 0) {
            temporada = service.listarId(id);
            if (temporada == null) {
                response = new HashMap<>();
                response.put("error", "1");
                response.put("response", "La temporada no existe.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } else {
            response = new HashMap<>();
            response.put("error", "1");
            response.put("response", "El id de la temporada no existe.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response = new HashMap<>();
        response.put("error", "0");
        response.put("response", temporada);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/temporadas")
    public ResponseEntity<?> guardar(@RequestBody Temporada temporada) {
        Temporada obj = service.guardar(temporada);
        Map<String, Object> response = new HashMap<>();
        response.put("error", "0");
        response.put("response", obj);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/temporadas")
    public ResponseEntity<?> editar(@RequestBody Temporada temporada) {
        Temporada obj = service.guardar(temporada);
        Map<String, Object> response = new HashMap<>();
        response.put("error", "0");
        response.put("response", obj);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/temporadas/{id}")
    public ResponseEntity<?> eliminar(@PathVariable(value = "id") Long id) {
        Temporada temporada = null;
        Map<String, Object> response = null;
        if (id > 0) {
            temporada = service.listarId(id);
            if (temporada == null) {
                response = new HashMap<>();
                response.put("error", "1");
                response.put("response", "La temporada no existe");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } else {
            response = new HashMap<>();
            response.put("error", "1");
            response.put("mensaje", "El id de la temporada no existe");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        service.eliminarId(id);
        response = new HashMap<>();
        response.put("error", "0");
        response.put("response", temporada);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
