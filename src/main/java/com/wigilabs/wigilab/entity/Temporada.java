/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wigilabs.wigilab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Desarrollo
 */
@Entity
@Table(name = "temporada")
public class Temporada implements Serializable {

    private static final long serialVersionUID = -2251023477308247022L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(length = 50,
            name = "nombre",
            nullable = false)
    @NotEmpty(message = "Debe ingresar un nombre.")
    @Size(min = 2, max = 50, message = "Debe ingresar entre 2-50 caracteres.")
    private String nombre;
    
    @Column(name = "duracion",
            nullable = false)
    @NotNull(message = "Debe ingresar la duración de la temporada.")
    @Min(message = "La duración deber ser mayor a: 0", value = 1)
    private int duracion;
    
    @ManyToOne
    @JoinColumn(name = "id_serie", nullable = false)
    private Serie serie;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY,
            mappedBy = "temporada")
    private List<Episodio> episodios;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the duracion
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * @return the serie
     */
    public Serie getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    /**
     * @return the episodios
     */
    public List<Episodio> getEpisodios() {
        return episodios;
    }

    /**
     * @param episodios the episodios to set
     */
    public void setEpisodios(List<Episodio> episodios) {
        this.episodios = episodios;
    }
}
