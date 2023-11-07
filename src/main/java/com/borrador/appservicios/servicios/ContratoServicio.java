<<<<<<< HEAD
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
    
    
    public void crearContrato(String idProveedor, String idCliente, Date fechaInicio){
        
        Optional<Cliente> respCliente = clienteRepositorio.findById(idCliente);
        Optional<Proveedor> respProveedor = proveedorRepositorio.findById(idCliente);
        
        if(respCliente.isPresent() && respProveedor.isPresent()){
            
            Contrato contrato = new Contrato();
            contrato.setCliente(respCliente.get());
            contrato.setProveedor(respProveedor.get());
            contrato.setFechaInicio(fechaInicio);
            contrato.setFechaFin(null);
            contrato.setPrecio(0);
            contrato.setContratoIniciado(true);
            contrato.setContratoFinalizado(false);
            contrato.setContratoCancelado(false);
            
            contratoRepositorio.save(contrato);
            
        }
        
        
        
        
    }
    
}
=======
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
    
    
    public void crearContrato(String idProveedor, String idCliente, Date fechaInicio){
        
        Optional<Cliente> respCliente = clienteRepositorio.findById(idCliente);
        Optional<Proveedor> respProveedor = proveedorRepositorio.findById(idCliente);
        
        if(respCliente.isPresent() && respProveedor.isPresent()){
            
            Contrato contrato = new Contrato();
            contrato.setCliente(respCliente.get());
            contrato.setProveedor(respProveedor.get());
            contrato.setFechaInicio(fechaInicio);
            contrato.setFechaFin(null);
            contrato.setPrecio(0);
            contrato.setContratoIniciado(true);
            contrato.setContratoFinalizado(false);
            contrato.setContratoCancelado(false);
            
            contratoRepositorio.save(contrato);
            
        }
        
        
        
        
    }
    
}
>>>>>>> 8a74cd5bd50f82952c07633e3955e4e293750b74
