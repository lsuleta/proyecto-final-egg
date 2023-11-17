/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borrador.appservicios.servicios;

import com.borrador.appservicios.entidades.Contrato;
import com.borrador.appservicios.entidades.Servicio;
import com.borrador.appservicios.entidades.Usuario;
import com.borrador.appservicios.enumeradores.Rol;
import com.borrador.appservicios.excepciones.Excepciones;
import com.borrador.appservicios.repositorios.ContratoRepositorio;
import com.borrador.appservicios.repositorios.ServicioRepositorio;
import com.borrador.appservicios.repositorios.UsuarioRepositorio;
import java.util.Date;
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
public class ContratoServicio {

    @Autowired
    ServicioRepositorio servicioRepositorio;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    ContratoRepositorio contratoRepositorio;

    // --- Creacion de contrato servicio --- //
    @Transactional
    public void crearContrato(String idProveedor, String idCliente, String idServicio, Integer precio,
            String nombre, String apellido, String telefono, String direccion) throws Excepciones {

        validar(idProveedor, idCliente, idServicio, nombre, apellido, telefono, direccion);
        try {

            Optional<Usuario> respCliente = usuarioRepositorio.findById(idCliente);
            Optional<Usuario> respProveedor = usuarioRepositorio.findById(idProveedor);
            Optional<Servicio> respServicio = servicioRepositorio.findById(idServicio);

            if (respCliente.isPresent() && respProveedor.isPresent() && respServicio.isPresent()) {

                System.out.println("IDS ENCONTRADOS");
                System.out.println("ID CLIENTE - " + respCliente.get().getId());
                System.out.println("ID PROVEEDOR - " + respProveedor.get().getId());
                System.out.println("ID SERVICIO - " + respServicio.get().getId());

                Contrato contrato = new Contrato();

                Usuario cliente = respCliente.get();
                Usuario proveedor = respProveedor.get();
                Servicio servicio = respServicio.get();

                if (cliente.getRol() == Rol.USER) {
                    System.out.println("-------- MODIFICANDO ROL DE USER A CLIENTE");
                    cliente.setRol(Rol.CLIENTE);
                    System.out.println("-------- USUARIO NUEVO ROL " + cliente.getRol());
                }
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setTelefono(telefono);
                cliente.setDireccion(direccion);

                contrato.setFechaInicio(new Date());
                contrato.setFechaFin(null);

                contrato.setPrecio(precio);
                contrato.setContratoIniciado(true);
                contrato.setContratoFinalizado(false);
                contrato.setContratoCancelado(false);

                contrato.setCliente(cliente);
                contrato.setProveedor(proveedor);
                contrato.setServicio(servicio);

                System.out.println("SERVICIO PERSISTIENDO CONTRATO");
                contratoRepositorio.save(contrato);

            }

        } catch (Exception e) {
            throw new Excepciones("Servicio crear contrato falla");
        }

    }

    @Transactional
    public void contratoCancelar(String idContrato) {

        Optional<Contrato> resp = contratoRepositorio.findById(idContrato);
        System.out.println("Servicio cancelar Contrato id " + idContrato);
        if (resp.isPresent()) {
            Contrato contrato = resp.get();
            if (contrato.getContratoCancelado() == false) {
                contrato.setContratoCancelado(Boolean.TRUE);
                System.out.println(" --- ");
                System.out.println("Contrato ya esta cancelado");
                System.out.println(" --- ");
            } else {
                System.out.println(" --- ");
                System.out.println("El contrato ya esta cancelado");
                System.out.println(" --- ");
            }
        }

    }

    public void validar(String idProveedor, String idCliente, String idServicio,
            String nombre, String apellido, String telefono, String direccion) throws Excepciones {
        if (idProveedor.isEmpty() || idProveedor == null) {
            throw new Excepciones("ID Proveedor nulo o vacio");
        }
        if (idCliente.isEmpty() || idCliente == null) {
            throw new Excepciones("ID Cliente nulo o vacio");
        }
        if (idServicio.isEmpty() || idServicio == null) {
            throw new Excepciones("ID Cliente nulo o vacio");
        }
        if (nombre.isEmpty() || nombre == null) {
            throw new Excepciones("nombre Cliente nulo o vacio");
        }
        if (apellido.isEmpty() || apellido == null) {
            throw new Excepciones("apellido Cliente nulo o vacio");
        }
        if (telefono.isEmpty() || telefono == null) {
            throw new Excepciones("telefono Cliente nulo o vacio");
        }
        if (direccion.isEmpty() || direccion == null) {
            throw new Excepciones("direccion Cliente nulo o vacio");
        }

    }

    //  ---------- listas de contratos Usuarios, Proveedores, Mod, Admin ---------- //
    @Transactional(readOnly = true)
    public List<Contrato> listarContratosPorCliente(Usuario cliente) {
        return contratoRepositorio.findByCliente(cliente);
    }

    @Transactional(readOnly = true)
    public List<Contrato> listarContratosPorProveedor(Usuario proveedor) {
        return contratoRepositorio.findByProveedor(proveedor);
    }

    @Transactional
    public void calificacion(String idContrato, Integer num) {

        Optional<Contrato> respContrato = contratoRepositorio.findById(idContrato);
        if (respContrato.isPresent()) {

            Contrato contrato = respContrato.get();
            contrato.getCalificaciones().add(num);
            Double cali = promediar(contrato.getCalificaciones());
            contrato.setCalificacion(cali);
            contrato.getServicio().setCalificacionServicio(cali);
            contrato.setEstrellas(estrellasServicio(cali));
            contrato.getServicio().setEstrellasCalificacionServicio(estrellasServicio(cali));
            
            contrato.getProveedor().setPromedioGeneral(promediarGeneral(contrato.getProveedor().getServicios()));
            contrato.getProveedor().setPromedioGeneralString(estrellasServicio(contrato.getProveedor().getPromedioGeneral()));

            System.out.println("SERVICIO CALIFICACION ----- OK");
        } else {

            System.out.println("SERVICIO CALIFICACION ----- FAIL");
        }

    }

    public static Double promediarGeneral(List<Servicio> serviciosList){
        if (serviciosList == null || serviciosList.isEmpty()) {
            throw new IllegalArgumentException("La lista de números no puede ser nula o vacía");
        }
        int suma = 0;
        for (Servicio serv : serviciosList) {
            suma += serv.getCalificacionServicio();
        }

        return (double) suma / serviciosList.size();
        
    }
    
    public static Double promediar(List<Integer> listaCalificaciones) {
        if (listaCalificaciones == null || listaCalificaciones.isEmpty()) {
            throw new IllegalArgumentException("La lista de números no puede ser nula o vacía");
        }

        int suma = 0;
        for (Integer numero : listaCalificaciones) {
            suma += numero;
        }

        return (double) suma / listaCalificaciones.size();
    }

    public static String estrellasServicio(Double num) {

        Integer var = (int) Math.floor(num);

        switch (var) {
            case 1 -> {
                return "⭐";
            }
            case 2 -> {
                return "⭐⭐";
            }
            case 3 -> {
                return "⭐⭐⭐";
            }
            case 4 -> {
                return "⭐⭐⭐⭐";
            }
            case 5 -> {
                return "⭐⭐⭐⭐⭐";
            }

            default ->
                throw new AssertionError();
        }

    }

    @Transactional(readOnly = true)
    public List<Contrato> listarContratoIniciado(Usuario usuario) {
        return contratoRepositorio.findByClienteAndContratoIniciadoIsTrue(usuario);
    }
    @Transactional(readOnly = true)
    public List<Contrato> listarContratoCancelado(Usuario usuario) {
        return contratoRepositorio.findByClienteAndContratoCanceladoIsTrue(usuario);
    }
    @Transactional(readOnly = true)
    public List<Contrato> listarContratoFinalizado(Usuario usuario) {
        return contratoRepositorio.findByClienteAndContratoFinalizadoIsTrue(usuario);
    }

}
