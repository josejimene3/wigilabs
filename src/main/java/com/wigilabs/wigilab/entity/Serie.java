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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Desarrollo
 */
@Entity
@Table(name = "serie")
public class Serie implements Serializable {

    private static final long serialVersionUID = 3458346960054805038L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(length = 50,
            name = "nombre",
            nullable = false,
            unique = true)
    @NotEmpty(message = "Debe ingresar un nombre.")
    @Size(min = 2, max = 50, message = "Debe ingresar entre 2-50 caracteres.")
    private String nombre;
    
    @Column(length = 50,
            name = "genero",
            nullable = false)
    @NotEmpty(message = "Debe ingresar el género.")
    @Size(min = 2, max = 50, message = "Debe ingresar entre 2-50 caracteres.")
    private String genero;
    
    @Column(name = "calificacion",
            nullable = false)
    @NotNull(message = "Debe ingresar una calificación.")
    @Max(message = "La calificación máxima es: 5", value = 5)
    @Min(message = "La calificación mínima es: 1", value = 1)
    private int calificacion;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "serie")
    private List<Temporada> temporadas;    

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
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return the calificacion
     */
    public int getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the temporadas
     */
    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    /**
     * @param temporadas the temporadas to set
     */
    public void setTemporadas(List<Temporada> temporadas) {
        this.temporadas = temporadas;
    }
}
