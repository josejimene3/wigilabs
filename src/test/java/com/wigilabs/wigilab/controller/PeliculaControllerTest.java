/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wigilabs.wigilab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wigilabs.wigilab.entity.Pelicula;
import com.wigilabs.wigilab.service.PeliculaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.assertj.core.util.Arrays;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Desarrollo
 */
@WebMvcTest(PeliculaController.class)
public class PeliculaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private PeliculaService service;

    @Test
    public void listarId() throws Exception {
        Pelicula pelicula = new Pelicula();
        pelicula.setAno(1982);
        pelicula.setCalificacion(5);
        pelicula.setGenero("ACCIÓN");
        pelicula.setId(5L);
        pelicula.setNombre("TERMINATOR");

        Mockito.when(service.listarId(5L)).thenReturn(pelicula);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/peliculas/5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.error", is("0")));
    }

    @Test
    public void guardar() throws Exception {
        Pelicula pelicula = new Pelicula();

        pelicula.setAno(1992);
        pelicula.setCalificacion(5);
        pelicula.setGenero("DRAMA");
        pelicula.setNombre("MERLI");

        Mockito.when(service.guardar(pelicula)).thenReturn(pelicula);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/v1/peliculas")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(pelicula));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.error", is("0")));
    }

    @Test
    public void editar() throws Exception {
        Pelicula pelicula = new Pelicula();

        pelicula.setAno(1992);
        pelicula.setCalificacion(5);
        pelicula.setGenero("DRAMA");
        pelicula.setId(1L);
        pelicula.setNombre("MERLI");

        Mockito.when(service.listarId(pelicula.getId())).thenReturn(Optional.of(pelicula).orElse(null));
        Mockito.when(service.guardar(pelicula)).thenReturn(pelicula);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/v1/peliculas")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(pelicula));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.error", is("0")));
    }

    @Test
    public void eliminar() throws Exception {
        Pelicula pelicula = new Pelicula();

        pelicula.setAno(1992);
        pelicula.setCalificacion(5);
        pelicula.setGenero("DRAMA");
        pelicula.setId(1L);
        pelicula.setNombre("MERLI");
        Mockito.when(service.listarId(pelicula.getId())).thenReturn(Optional.of(pelicula).orElse(null));

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/peliculas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
