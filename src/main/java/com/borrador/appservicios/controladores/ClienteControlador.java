/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borrador.appservicios.controladores;

import com.borrador.appservicios.entidades.Contrato;
import com.borrador.appservicios.entidades.Usuario;
import com.borrador.appservicios.enumeradores.Rol;
import com.borrador.appservicios.excepciones.Excepciones;
import com.borrador.appservicios.servicios.ContratoServicio;
import com.borrador.appservicios.servicios.ServicioServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private ContratoServicio contratoServicio;

    @Autowired
    private ServicioServicio servicioServicio;

    // --- Vista de CONTRATO SERVICIO --- //
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_CLIENTE', 'ROLE_PROVEEDOR', 'ROLE_ADMIN', 'ROLE_MODERADOR')")
    @GetMapping("/cita/{id}")
    public String citaProgramada(@PathVariable String id, ModelMap modelo) {
        modelo.addAttribute("servicio", servicioServicio.buscarServicioPorId(id));
        return "contrato.html";
    }

    // --- CONFIRMACION DE CONTRATO - FORMULARIO POST --- //
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_CLIENTE', 'ROLE_PROVEEDOR', 'ROLE_ADMIN', 'ROLE_MODERADOR')")
    @PostMapping("/cita/{id}")
    public String contratoCita(@RequestParam String idCliente, @RequestParam String idProveedor, @RequestParam String idServicio,
            @RequestParam Integer precio, @RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String telefono, @RequestParam String direccion, HttpSession session,
            ModelMap modelo) throws Excepciones {

        try {

            Usuario usuario = (Usuario) session.getAttribute("usuariosession");

            System.out.println(" --- ");
            System.out.println("CONTRATO POR CONFIRMAR");
            System.out.println(" --- ");
            System.out.println(" --- CONTROLADOR CLIENTE... id Servicio : " + idServicio);

            contratoServicio.crearContrato(idProveedor, idCliente, idServicio, precio, nombre, apellido, telefono, direccion);
            System.out.println(" --- ");
            System.out.println(" CONTRATO CONFIRMADO --- OK");
            System.out.println(" --- ");

            session.setAttribute("usuariosession", usuario);

            return "redirect:/servicios-contratados/" + idCliente;
        } catch (Exception e) {
            throw new Excepciones("ERROR CREAR CLIENTE CONTROLADOR");
        }

    }

    // --- CANCELAR CONTRATO --- //
    @GetMapping("/cancelar-contrato/{id}")
    public String cancelarContrato(@PathVariable String id) throws Excepciones {
        try {
            System.out.println("Tratanco de cancelar contrato de id " + id);
            contratoServicio.contratoCancelar(id);
            return "perfiles.html";

        } catch (Exception e) {
            throw new Excepciones("FALLA AL TRATAR DE CANCELAR CONTRATO");
        }
    }

    @PostMapping("/calificacion/{id}")
    public String calificar(@PathVariable String id, @RequestParam String idContrato, @RequestParam Integer num) throws Excepciones {

        System.out.println("EL ID del URL  ES = " + id);
        System.out.println("EL ID del CONTRATO  ES = " + idContrato);
        System.out.println("EL NUMERO INGRESADO ES = " + num + " * ");

        try {
            contratoServicio.calificacion(idContrato, num);
            System.out.println("CALIFICACION ---- OK  ");
        } catch (Exception e) {
            throw new Excepciones("FALLO CONTROLADOR CALIFICAR");
        }

        return "redirect:/servicios-contratados/" + id;
    }

    // --- FILTROS CONTRATOS --- //
    @PostMapping("/filtro-contrato/{id}")
    public String filtrarContrato(@PathVariable String id, @RequestParam String filtro) {

        System.out.println("SE ESTA INTENTANDO ACCEDER AL FITRO CONTRATO DE "+filtro);
        System.out.println("EL ID INGRESADO POR URL ES "+id);
        
        if (filtro.equalsIgnoreCase("todos")) {
            return "redirect:/servicios-contratados/"+id;
        } else if (filtro.equalsIgnoreCase("activo")) {
            return "redirect:/servicios-contratados-iniciado/"+id;
        } else if (filtro.equalsIgnoreCase("cancelado")) {
            return "redirect:/servicios-contratados-cancelado/"+id;
        } else if (filtro.equalsIgnoreCase("fin")) {
            return "redirect:/servicios-contratados-finalizado/"+id;
        } else {
            return "redirect:/servicios-contratados/"+id;
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_CLIENTE', 'ROLE_PROVEEDOR', 'ROLE_ADMIN', 'ROLE_MODERADOR')")
    @GetMapping("/servicios-contratados-iniciado/{id}")
    public String filtroContratoIniciado(@PathVariable String id, HttpSession session, ModelMap modelo) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        modelo.addAttribute("usuario", usuario);

        if (usuario.getRol() == Rol.CLIENTE) {
            // TRAE CONTRATOS CLIENTE
            List<Contrato> contratosCliente = contratoServicio.listarContratoIniciado(usuario);
            modelo.addAttribute("contratos", contratosCliente);
        }
        if (usuario.getRol() == Rol.PROVEEDOR) {
            // TRAE CONTRATOS PROVEEDOR
            List<Contrato> contratosProveedor = contratoServicio.listarContratoIniciado(usuario);
            modelo.addAttribute("contratos", contratosProveedor);
        }
        if (usuario.getRol() == Rol.MODERADOR) {
            // TRAE CONTRATOS MODERADOR
            List<Contrato> contratosModerador = contratoServicio.listarContratoIniciado(usuario);
            modelo.addAttribute("contratos", contratosModerador);
        }
        if (usuario.getRol() == Rol.ADMIN) {
            // TRAE CONTRATOS MODERADOR
            List<Contrato> contratosAdministrador = contratoServicio.listarContratoIniciado(usuario);
            modelo.addAttribute("contratos", contratosAdministrador);
        }

        return "servicios_contratados_lista.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_CLIENTE', 'ROLE_PROVEEDOR', 'ROLE_ADMIN', 'ROLE_MODERADOR')")
    @GetMapping("/servicios-contratados-cancelado/{id}")
    public String filtroContratoCancelado(@PathVariable String id, HttpSession session, ModelMap modelo) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        modelo.addAttribute("usuario", usuario);

        if (usuario.getRol() == Rol.CLIENTE) {
            // TRAE CONTRATOS CLIENTE
            List<Contrato> contratosCliente = contratoServicio.listarContratoCancelado(usuario);
            modelo.addAttribute("contratos", contratosCliente);
        }
        if (usuario.getRol() == Rol.PROVEEDOR) {
            // TRAE CONTRATOS PROVEEDOR
            List<Contrato> contratosProveedor = contratoServicio.listarContratoCancelado(usuario);
            modelo.addAttribute("contratos", contratosProveedor);
        }
        if (usuario.getRol() == Rol.MODERADOR) {
            // TRAE CONTRATOS MODERADOR
            List<Contrato> contratosModerador = contratoServicio.listarContratoCancelado(usuario);
            modelo.addAttribute("contratos", contratosModerador);
        }
        if (usuario.getRol() == Rol.ADMIN) {
            // TRAE CONTRATOS MODERADOR
            List<Contrato> contratosAdministrador = contratoServicio.listarContratoCancelado(usuario);
            modelo.addAttribute("contratos", contratosAdministrador);
        }

        return "servicios_contratados_lista.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_CLIENTE', 'ROLE_PROVEEDOR', 'ROLE_ADMIN', 'ROLE_MODERADOR')")
    @GetMapping("/servicios-contratados-finalizado/{id}")
    public String filtroContratoFinalizadoo(@PathVariable String id, HttpSession session, ModelMap modelo) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        modelo.addAttribute("usuario", usuario);

        if (usuario.getRol() == Rol.CLIENTE) {
            // TRAE CONTRATOS CLIENTE
            List<Contrato> contratosCliente = contratoServicio.listarContratoFinalizado(usuario);
            modelo.addAttribute("contratos", contratosCliente);
        }
        if (usuario.getRol() == Rol.PROVEEDOR) {
            // TRAE CONTRATOS PROVEEDOR
            List<Contrato> contratosProveedor = contratoServicio.listarContratoFinalizado(usuario);
            modelo.addAttribute("contratos", contratosProveedor);
        }
        if (usuario.getRol() == Rol.MODERADOR) {
            // TRAE CONTRATOS MODERADOR
            List<Contrato> contratosModerador = contratoServicio.listarContratoFinalizado(usuario);
            modelo.addAttribute("contratos", contratosModerador);
        }
        if (usuario.getRol() == Rol.ADMIN) {
            // TRAE CONTRATOS MODERADOR
            List<Contrato> contratosAdministrador = contratoServicio.listarContratoFinalizado(usuario);
            modelo.addAttribute("contratos", contratosAdministrador);
        }

        return "servicios_contratados_lista.html";
    }

}
