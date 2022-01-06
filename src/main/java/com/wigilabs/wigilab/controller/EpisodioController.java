/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wigilabs.wigilab.controller;

import com.wigilabs.wigilab.entity.Episodio;
import com.wigilabs.wigilab.service.EpisodioService;
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
public class EpisodioController {

    @Autowired
    private EpisodioService service;

    @GetMapping("/episodios")
    public ResponseEntity<?> listar(Pageable pageable) {
        Page<Episodio> pages = service.listar(pageable);

        Map<String, Object> page = new HashMap<>();
        page.put("currentPage", pages.getNumber());
        page.put("totalPages", pages.getTotalPages());
        page.put("totalItems", pages.getTotalElements());
        page.put("size", pages.getSize());
        page.put("episodios", pages.getContent());

        Map<String, Object> response = new HashMap<>();
        response.put("error", "0");
        response.put("response", page);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/episodios/{id}")
    public ResponseEntity<?> listarId(@PathVariable(value = "id") Long id) {
        Episodio episodio = null;
        Map<String, Object> response = null;
        if (id > 0) {
            episodio = service.listarId(id);
            if (episodio == null) {
                response = new HashMap<>();
                response.put("error", "1");
                response.put("response", "El episodio no existe.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } else {
            response = new HashMap<>();
            response.put("error", "1");
            response.put("response", "El id del episodio no existe.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response = new HashMap<>();
        response.put("error", "0");
        response.put("response", episodio);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/episodios")
    public ResponseEntity<?> guardar(@RequestBody Episodio episodio) {
        Episodio obj = service.guardar(episodio);
        Map<String, Object> response = new HashMap<>();
        response.put("error", "0");
        response.put("response", obj);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/episodios")
    public ResponseEntity<?> editar(@RequestBody Episodio episodio) {
        Episodio obj = service.guardar(episodio);
        Map<String, Object> response = new HashMap<>();
        response.put("error", "0");
        response.put("response", obj);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/episodios/{id}")
    public ResponseEntity<?> eliminar(@PathVariable(value = "id") Long id) {
        Episodio episodio = null;
        Map<String, Object> response = null;
        if (id > 0) {
            episodio = service.listarId(id);
            if (episodio == null) {
                response = new HashMap<>();
                response.put("error", "1");
                response.put("response", "El episodio no existe");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } else {
            response = new HashMap<>();
            response.put("error", "1");
            response.put("response", "El id del episodio no existe");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        service.eliminarId(id);
        response = new HashMap<>();
        response.put("error", "0");
        response.put("response", episodio);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
