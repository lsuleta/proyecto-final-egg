package com.borrador.appservicios.controladores;

import com.borrador.appservicios.Exception.MiException;
import com.borrador.appservicios.entidades.Proveedor;
import com.borrador.appservicios.entidades.Usuario;
import com.borrador.appservicios.enumeradores.Categoria;
import com.borrador.appservicios.excepciones.Excepciones;
import com.borrador.appservicios.servicios.ClienteServicio;
import com.borrador.appservicios.servicios.ComentarioServicio;
import com.borrador.appservicios.servicios.ProveedorServicio;
import com.borrador.appservicios.servicios.ServicioServicio;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private ServicioServicio servicioServicio;

    
    
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
          
            comentarioServicio.persistirComentario(comentario, id, idProveedor);

            modelo.put("exito", "COMENTARIO REALIZADO CORRECTAMENTE");
            return "redirect:/servicios/proveedor/"+idProveedor;
        } catch (Excepciones ex) {
            System.out.println("1----------- EXCEPCION DE CARGA DE COMENTARIO");
            Logger.getLogger(IndexControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "servicio_pruebas.html";
    }
    
    
    
//        //---------- Formulario ------------//
//    @PostMapping("/servicios/proveedor-contrato/{id}")
//    public String enviarFormulario(
//            @RequestParam String id,
//            @PathVariable String idProveedor,
//            @RequestParam String nombre,
//            @RequestParam String apellido,
//            @RequestParam String telefono,
//            @RequestParam String direccion,
//            ModelMap modelo){
//
//        try {
//           clienteServicio.crearCliente(id, nombre, apellido, direccion, telefono);
//            System.out.println("Cliente creado en formulario");
//        } catch (Excepciones ex) {
//            System.out.println("Error en crear cliente en formulario");
//        }
//
//        return "servicio_pruebas.html";
//    }
    
    @GetMapping("registro-servicio/{id}")
    public String registroServicio(ModelMap modelo){
         
        modelo.addAttribute("categorias", Categoria.values());
        
        return "registrar_servicio_form.html";
    }
    
    @PostMapping("/registrar-servicio/{id}")
    public String crearServicio(@PathVariable String id,@RequestParam String descripcionServicio,
            @RequestParam Integer precioServicio){
        
        try {
            servicioServicio.crearServicio(id, descripcionServicio , precioServicio);
            System.out.println("CONTROLADOR PROVEEDOR--- SERVICIO CARGADO---POST");
            return "redirect:/perfil/"+id;
        } catch (Excepciones e) {
            System.out.println("CATCH ERROR EN POST SERVICIO CREAR SERVICIO");
            return "index.html";
        }
        
        
    }
    
    
    
}
