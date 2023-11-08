/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borrador.appservicios.servicios;

import com.borrador.appservicios.entidades.Cliente;
import com.borrador.appservicios.entidades.Contrato;
import com.borrador.appservicios.entidades.Proveedor;
import com.borrador.appservicios.repositorios.ClienteRepositorio;
import com.borrador.appservicios.repositorios.ContratoRepositorio;
import com.borrador.appservicios.repositorios.ProveedorRepositorio;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author facun
 */
@Service
public class ContratoServicio {
    
    @Autowired
    ClienteRepositorio clienteRepositorio;
    
    @Autowired
    ProveedorRepositorio proveedorRepositorio;
    
    @Autowired
    ContratoRepositorio contratoRepositorio;
    
    
    public void crearContrato(String idProveedor, String idCliente, int precio){
        
        Optional<Cliente> respCliente = clienteRepositorio.findById(idCliente);
        Optional<Proveedor> respProveedor = proveedorRepositorio.findById(idProveedor);
        
        if(respCliente.isPresent() && respProveedor.isPresent()){
            System.out.println("IDS ENCONTRADOS");
                    
            Contrato contrato = new Contrato();
            contrato.setCliente(respCliente.get());
            contrato.setProveedor(respProveedor.get());
            contrato.setFechaInicio(new Date());
            contrato.setFechaFin(null);
            contrato.setPrecio(precio);
            contrato.setContratoIniciado(true);
            contrato.setContratoFinalizado(false);
            contrato.setContratoCancelado(false);
            
            respCliente.get().getServiciosContratados().add(contrato);
            respProveedor.get().getContratos().add(contrato);
            
            System.out.println("SERVICIO PERSISTIENDO CONTRATO");
            contratoRepositorio.save(contrato);
            
        }
        
        
        
        
    }
    
}
