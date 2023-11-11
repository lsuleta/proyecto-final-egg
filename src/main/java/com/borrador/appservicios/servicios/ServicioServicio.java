/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borrador.appservicios.servicios;

import com.borrador.appservicios.entidades.Contrato;
<<<<<<< HEAD
import com.borrador.appservicios.entidades.Proveedor;
import com.borrador.appservicios.entidades.Servicio;
import com.borrador.appservicios.enumeradores.Categoria;
import com.borrador.appservicios.excepciones.Excepciones;
import com.borrador.appservicios.repositorios.ContratoRepositorio;
import com.borrador.appservicios.repositorios.ProveedorRepositorio;
import com.borrador.appservicios.repositorios.ServicioRepositorio;
=======
import com.borrador.appservicios.entidades.Servicio;
import com.borrador.appservicios.entidades.Usuario;
import com.borrador.appservicios.enumeradores.Categoria;
import com.borrador.appservicios.excepciones.Excepciones;
import com.borrador.appservicios.repositorios.ContratoRepositorio;
import com.borrador.appservicios.repositorios.ServicioRepositorio;
import com.borrador.appservicios.repositorios.UsuarioRepositorio;
>>>>>>> c739c42b1cf8daeadef67f459ed2cd5ca750b457
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    @Autowired
<<<<<<< HEAD
    ProveedorRepositorio proveedorRepositorio;
=======
    UsuarioRepositorio usuarioRepositorio;
>>>>>>> c739c42b1cf8daeadef67f459ed2cd5ca750b457

    
    @Transactional
    public void crearServicio(String idProveedor, String descripcionServicio,
            Integer precioServicio) throws Excepciones {

<<<<<<< HEAD
        Optional<Proveedor> resp = proveedorRepositorio.findById(idProveedor);
        if (resp.isPresent()) {

            Proveedor proveedor = resp.get();
=======
        Optional<Usuario> resp = usuarioRepositorio.findById(idProveedor);
        if (resp.isPresent()) {

            Usuario proveedor = resp.get();
>>>>>>> c739c42b1cf8daeadef67f459ed2cd5ca750b457
            Servicio servicio = new Servicio();

            servicio.setProveedor(proveedor);
            servicio.setDescripcionServicio(descripcionServicio);
            //  servicio.setCategoriaServicio(categoriaServicio);
            servicio.setPrecioServicio(precioServicio);

            //   servicio.setContrato(null);
            //   servicio.setCalificacion(null);
            System.out.println("CARGANDO SERVICIO A PROVEEDOR" + proveedor.getNombre());
            proveedor.getSevicios().add(servicio);
            System.out.println("CARGANDO SERVICIO EN BASE DE DATOS..  " + servicio.getDescripcionServicio());

            servicioRepositorio.save(servicio);
        }
    }



}
