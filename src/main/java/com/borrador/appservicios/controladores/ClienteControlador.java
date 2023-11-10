/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borrador.appservicios.controladores;

import com.borrador.appservicios.excepciones.Excepciones;
import com.borrador.appservicios.repositorios.ServicioRepositorio;
import com.borrador.appservicios.servicios.ClienteServicio;
import com.borrador.appservicios.servicios.ContratoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author facun
 */
@Controller
public class ClienteControlador {

    @Autowired
    private ClienteServicio clienteServcio;

    @Autowired
    private ServicioRepositorio servicioRepositorio;

    @Autowired
    private ContratoServicio contratoServicio;

    @PostMapping("/registrar-cliente")
    public String registrarCliente(@RequestParam String nombre, @RequestParam String email, @RequestParam String apellido,
            @RequestParam String telefono, @RequestParam String direccion) throws Excepciones {

        try {
            System.out.println("CREANDO CLIENTE");
            clienteServcio.crearCliente(email, nombre, apellido, direccion, telefono);
            System.out.println("CLIENTE CREADO --- USUARIO ACTUALIZADO A CLIENTE");
            return "servicios_pruebas.html";
        } catch (Excepciones e) {
            throw new Excepciones("ERROR AL CREAR CLIENTE CONTROLADOR");
        }

    }

    @GetMapping("/cita/{id}")
    public String citaProgramada(@PathVariable String id, ModelMap modelo) {
        modelo.addAttribute("servicio", servicioRepositorio.getOne(id));
        return "contrato.html";
    }

    @PostMapping("/cita/{id}")
    public String contratoCita(@RequestParam String idCliente, @RequestParam String idProveedor,
             @RequestParam String idServicio, @RequestParam Integer precio,
            ModelMap modelo) throws Excepciones {

        try {
            System.out.println("CLIENTE CONTROLADOR CREANDO CONTRATO");
            contratoServicio.crearContrato(idProveedor, idCliente, idServicio, precio);
            System.out.println("CLIENTE CONTROLADOR CONTRATO CREADO --- OK");
            return "index.html";
        } catch (Exception e) {
            throw new Excepciones("ERROR CREAR CLIENTE CONTROLADOR");
        }

        
    }

}
