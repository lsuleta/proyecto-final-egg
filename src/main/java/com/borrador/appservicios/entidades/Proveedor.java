/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borrador.appservicios.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.List;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author facun
 */
@Entity
public class Proveedor extends Usuario {
    
    @Id
    @GeneratedValue(generator = "uuid")// Generar id alfanumerico unico
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String servicioTipo;
    private String servicioDescripcion;
    private Integer calificacion;
    
    @OneToMany
    private List<Usuario> clientes;
    
    @OneToOne 
    private Imagen imagen;


    public Proveedor() {
    }

    public String getServicioTipo() {
        return servicioTipo;
    }

    public void setServicioTipo(String servicioTipo) {
        this.servicioTipo = servicioTipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    
    public String getServicioDescripcion() {
        return servicioDescripcion;
    }

    public void setServicioDescripcion(String servicioDescripcion) {
        this.servicioDescripcion = servicioDescripcion;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public List<Usuario> getClientes() {
        return clientes;
    }

    public void setClientes(List<Usuario> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "servicioDescripcion=" + servicioDescripcion + ", calificacion=" + calificacion + ", clientes=" + clientes + '}';
    }

   
    
    
    
}
