/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wigilabs.wigilab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wigilabs.wigilab.entity.Episodio;
import com.wigilabs.wigilab.entity.Temporada;
import com.wigilabs.wigilab.service.EpisodioService;
import java.util.Optional;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
@WebMvcTest(EpisodioController.class)
public class EpisodioControllerTest {
    
    @Autowired
    MockMvc mockMvc;
    
    @Autowired
    ObjectMapper mapper;
    
    @MockBean
    private EpisodioService service;
    
    @Test
    public void listarId() throws Exception{
        
        Episodio episodio = new Episodio();
        episodio.setDuracion(45);
        episodio.setId(19L);
        episodio.setNombre("EPISODIO 1");

        Mockito.when(service.listarId(19L)).thenReturn(episodio);
        
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/episodios/19")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.error", is("0")));
    }
    
    @Test
    public void guardar() throws Exception {
        Episodio episodio = new Episodio();

        episodio.setDuracion(30);
        episodio.setNombre("MERLI");
        episodio.setTemporada(new Temporada());
        episodio.getTemporada().setDuracion(30);
        episodio.getTemporada().setId(1L);
        episodio.getTemporada().setNombre("TEMPORADA 1");

        Mockito.when(service.guardar(episodio)).thenReturn(episodio);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/v1/episodios")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(episodio));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.error", is("0")));
    }

    @Test
    public void editar() throws Exception {
        Episodio episodio = new Episodio();

        episodio.setDuracion(30);
        episodio.setId(1L);
        episodio.setNombre("MERLI");
        episodio.setTemporada(new Temporada());
        episodio.getTemporada().setDuracion(30);
        episodio.getTemporada().setId(1L);
        episodio.getTemporada().setNombre("TEMPORADA 1");

        Mockito.when(service.listarId(episodio.getId())).thenReturn(Optional.of(episodio).orElse(null));
        Mockito.when(service.guardar(episodio)).thenReturn(episodio);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/v1/episodios")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(episodio));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.error", is("0")));
    }

    @Test
    public void eliminar() throws Exception {
        Episodio episodio = new Episodio();

        episodio.setDuracion(30);
        episodio.setId(1L);
        episodio.setNombre("MERLI");
        
        Mockito.when(service.listarId(episodio.getId())).thenReturn(Optional.of(episodio).orElse(null));

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/episodios/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
