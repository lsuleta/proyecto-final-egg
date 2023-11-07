package com.borrador.appservicios.controladores;

import com.borrador.appservicios.Exception.MiException;
import com.borrador.appservicios.entidades.Usuario;
import com.borrador.appservicios.enumeradores.Categoria;
import com.borrador.appservicios.excepciones.Excepciones;
import com.borrador.appservicios.servicios.ComentarioServicio;
import com.borrador.appservicios.servicios.ProveedorServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author lsule
 */
@Controller
@RequestMapping("/")
public class ProveedorControlador {

    @Autowired
    private ProveedorServicio proveedorServicio;

    @Autowired
    private ComentarioServicio comentarioServicio;

    
    
    @GetMapping("/registrar-proveedor")
    public String registrarProveedor() {

        return "usuario_registro.html";
    }

    @PostMapping("/registro2")
    public String registrarProveedor(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String telefono,
            @RequestParam Categoria categoria,@RequestParam String email, @RequestParam String password,
            @RequestParam String password2, ModelMap modelo, @RequestParam(required = false) MultipartFile archivo) throws MiException {
        try {
            proveedorServicio.crearProveedor(nombre, apellido, telefono, email, password, password2, categoria, archivo);
            modelo.put("exito", "proveedor registrado correctamente");
            return "redirect:/";

        } catch (Excepciones ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);
            return "redirect:/";
        }
    }
    
    
    
    
    //-------------- Servicios --------------//
    @GetMapping("/servicios")
    public String servicios(ModelMap modelo) {
        List<Usuario> proveedores = proveedorServicio.listarProveedores();
        List<Usuario> electricidad = proveedorServicio.listarElectricidad();
        List<Usuario> plomeria = proveedorServicio.listarPlomeria();
        List<Usuario> salud = proveedorServicio.listarSalud();
        List<Usuario> limpieza = proveedorServicio.listarLimpieza();
        List<Usuario> jardineria = proveedorServicio.listarJardineria();
        List<Usuario> varios = proveedorServicio.listarVarios();

        modelo.addAttribute("plomeria", plomeria);
        modelo.addAttribute("electricidad", electricidad);
        modelo.addAttribute("salud", salud);
        modelo.addAttribute("limpieza", limpieza);
        modelo.addAttribute("jardineria", jardineria);
        modelo.addAttribute("varios", varios);

        return "servicios_pruebas.html";
    }

    @GetMapping("/servicios/proveedor/{id}")
    public String servicios(ModelMap modelo, @PathVariable String id) {
        
            modelo.put("proveedor", proveedorServicio.getOne(id));

            return "servicio_pruebas.html";
    }
    
    
        //---------- Comentarios ------------//
    @PostMapping("/servicios/proveedor/{id}")
    public String comentar(@RequestParam String comentario,
            @RequestParam String id,// id de usuario
            @RequestParam String idProveedor,
            ModelMap modelo) {

        try {
            System.out.println("1----- TRATANDO DE CARGAR COMENTARIO");
            comentarioServicio.persistirComentario(comentario, id, idProveedor);
            System.out.println("1----- COMENTARIO REALIZADO CORRECTAMENTE");
            modelo.put("exito", "COMENTARIO REALIZADO CORRECTAMENTE");
            return "redirect:/servicios/proveedor/"+idProveedor;
        } catch (Excepciones ex) {
            System.out.println("1----------- EXCEPCION DE CARGA DE COMENTARIO");
            Logger.getLogger(IndexControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "servicio_pruebas.html";
    }
    
}
