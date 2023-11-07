/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borrador.appservicios.servicios;

import com.borrador.appservicios.entidades.Contrato;
import com.borrador.appservicios.entidades.Servicio;
import com.borrador.appservicios.enumeradores.Categoria;
import com.borrador.appservicios.repositorios.ContratoRepositorio;
import com.borrador.appservicios.repositorios.ServicioRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author facun
 */

@Service
public class ServicioServicio {
    
    @Autowired
    ContratoRepositorio contratoRepositorio;
    
    @Autowired
    ServicioRepositorio servicioRepositorio;
    
    public void crearServicio(String idContrato, String descripcionServicio,
            Categoria categoria){

        Optional<Contrato> resp =  contratoRepositorio.findById(idContrato);
        
        if(resp.isPresent()){
            
            Contrato contrato = resp.get();
            Servicio servicio = new Servicio();
            servicio.setContrato(contrato);
            servicio.setCategoria(categoria);
            servicio.setDescripcionServicio(descripcionServicio);
            servicio.setCalificacion(null);
            
            servicioRepositorio.save(servicio);
            
        }
        
    }
    
    
}
