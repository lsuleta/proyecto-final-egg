/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borrador.appservicios.servicios;

<<<<<<< HEAD
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
=======
import com.borrador.appservicios.entidades.Contrato;
import com.borrador.appservicios.entidades.Usuario;
import com.borrador.appservicios.excepciones.Excepciones;
import com.borrador.appservicios.repositorios.ContratoRepositorio;
import com.borrador.appservicios.repositorios.UsuarioRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
>>>>>>> c739c42b1cf8daeadef67f459ed2cd5ca750b457

/**
 *
 * @author facun
 */
@Service
public class ContratoServicio {
<<<<<<< HEAD
    
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
    
=======

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    ContratoRepositorio contratoRepositorio;

    public void crearContrato(String idProveedor, String idCliente, String idServicio, Integer precio) throws Excepciones {

        validar(idProveedor, idCliente, idServicio);
        try {

            Optional<Usuario> respCliente = usuarioRepositorio.findById(idCliente);
            Optional<Usuario> respProveedor = usuarioRepositorio.findById(idProveedor);

            if (respCliente.isPresent() && respProveedor.isPresent()) {
                System.out.println("IDS ENCONTRADOS");
                System.out.println("ID CLIENTE - " + respCliente.get().getId());
                System.out.println("ID PROVEEDOR - " + respProveedor.get().getId());

                Contrato contrato = new Contrato();

                System.out.println("EL ID DEL CONTRATO GENERADO ES: " + contrato.getId());

                Usuario cliente = respCliente.get();
                Usuario proveedor = respProveedor.get();

                contrato.setFechaInicio(new Date());
                contrato.setFechaFin(null);

                contrato.setPrecio(precio);
                contrato.setContratoIniciado(true);
                contrato.setContratoFinalizado(false);
                contrato.setContratoCancelado(false);

//                cliente.getContratos().add(contrato);
//                proveedor.getContratos().add(contrato);
                contrato.setCliente(cliente);
                contrato.setProveedor(proveedor);

                System.out.println("EL ID DEL CONTRATO GENERADO ES: " + contrato.getId());

                System.out.println("SERVICIO PERSISTIENDO CONTRATO");
                contratoRepositorio.save(contrato);

            }

        } catch (Exception e) {
            throw new Excepciones("Servicio crear contrato falla");
        }

    }

    public void validar(String idProveedor, String idCliente, String idServicio) throws Excepciones {
        if (idProveedor.isEmpty() || idProveedor == null) {
            throw new Excepciones("ID Proveedor nulo o vacio");
        }
        if (idCliente.isEmpty() || idCliente == null) {
            throw new Excepciones("ID Cliente nulo o vacio");
        }
        if (idServicio.isEmpty() || idServicio == null) {
            throw new Excepciones("ID Cliente nulo o vacio");
        }

    }

    //  ---------- listas de contratos Usuarios y Proveedores ---------- //

    @Transactional(readOnly = true)
     public List<Contrato> findContratosByCliente(Usuario cliente) {
        return contratoRepositorio.findByCliente(cliente);
    }
     
    @Transactional(readOnly = true)
    public List<Contrato> findContratosByProveedor(Usuario proveedor) {
        return contratoRepositorio.findByProveedor(proveedor);
    }

>>>>>>> c739c42b1cf8daeadef67f459ed2cd5ca750b457
}
