package com.borrador.appservicios.controladores;

import com.borrador.appservicios.Exception.MiException;
import com.borrador.appservicios.entidades.Contrato;
import com.borrador.appservicios.entidades.Usuario;
import com.borrador.appservicios.enumeradores.Categoria;
import com.borrador.appservicios.enumeradores.Rol;

import com.borrador.appservicios.excepciones.Excepciones;
import com.borrador.appservicios.repositorios.UsuarioRepositorio;
import com.borrador.appservicios.servicios.ComentarioServicio;
import com.borrador.appservicios.servicios.ContratoServicio;
import com.borrador.appservicios.servicios.ServicioServicio;
import com.borrador.appservicios.servicios.UsuarioServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author kyouma
 */
@Controller
@RequestMapping("/")
public class IndexControlador {

    Usuario usuario;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ContratoServicio contratoServicio;

    @GetMapping("/")
    public String Index() {
        return "index.html";
    }

    @GetMapping("/registrar-usuario")
    public String registrarUsuario(HttpSession session, ModelMap modelo) {
        modelo.addAttribute("categorias", Categoria.values());
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        if (logueado != null) {
            return "redirect:/";
        } else {
            // return "usuario_registro.html";
            return "usuario_registro_pruebas.html";
        }
    }


    @PostMapping("/registro")
    public String registrarUsuario(@RequestParam String email, @RequestParam String password,
            @RequestParam String password2, ModelMap modelo, @RequestParam(required = false) MultipartFile archivo) {

        try {
            usuarioServicio.persistirUsuario(email, password, password2, archivo);
            modelo.put("exito", "usuario registrado correctamente");
            return "redirect:/";

        } catch (Excepciones ex) {
            modelo.put("error", ex.getMessage());
            //  modelo.put("nombre", nombre);
            modelo.put("email", email);
            return "redirect:/";
        }
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo, String password, HttpSession session) {
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");

        if (error != null) {
            session.invalidate();
            modelo.put("error", "Lo sentimos, el usuario o la contraseña no coinciden.");

            System.out.println("");
            return "login.html"; // Retornar inmediatamente en caso de error

        }
        if (logueado != null) {

            System.out.println("");
            return "redirect:/";
        } else {
            return "login.html";
        }

    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_CLIENTE' , 'ROLE_PROVEEDOR', 'ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session, ModelMap modelo) {

        Usuario logueado = (Usuario) session.getAttribute("usuariosession");

        if (logueado.getRol().toString().equals("ADMIN")) {
            return "redirect:/admin/dashboard";
        }

        return "redirect:/";
    }


    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_CLIENTE' , 'ROLE_PROVEEDOR', 'ROLE_ADMIN')")
    @GetMapping("/perfil/{id}")
    public String perfilUsuario(@PathVariable String id, HttpSession session, ModelMap modelo) {
        Usuario usuario = usuarioServicio.getOne(id);
        modelo.addAttribute("usuario", usuario);

        if (usuario.getRol() == Rol.CLIENTE) {
            // TRAE CONTRATOS CLIENTE
            List<Contrato> contratosCliente = contratoServicio.findContratosByCliente(usuario);
            modelo.addAttribute("contratos", contratosCliente);
        } else if (usuario.getRol() == Rol.PROVEEDOR) {
            // TRAE CONTRATOS PROVEEDOR
            List<Contrato> contratosProveedor = contratoServicio.findContratosByProveedor(usuario);
            modelo.addAttribute("contratos", contratosProveedor);
        }

        return "perfiles.html";
    }
    
    

    @GetMapping("/proveedor-registro/{id}")
    public String proveedorFormulario() {

        return "proveedor_registro.html";
    }

    //ver perfil
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_CLIENTE' , 'ROLE_PROVEEDOR', 'ROLE_ADMIN')")
    @GetMapping("/perfils/{id}")
    public String perfil(ModelMap modelo, HttpSession session) {
        usuario = (Usuario) session.getAttribute("usuariosession");
        modelo.put("usuario", usuario);

        return "modificar_cliente.html";
    }

    ///funcion actualizar datos de perfil
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_CLIENTE' , 'ROLE_PROVEEDOR', 'ROLE_ADMIN')")
    @PostMapping("/perfils/{id}")
    public String actualizar(MultipartFile archivo, @PathVariable String id, @RequestParam String email,
            @RequestParam String password, @RequestParam String password2, ModelMap modelo, HttpSession session) {

        try {
            Usuario usuarioactualizado = usuarioServicio.actualizar(archivo, id, email, password, password2);

            modelo.put("exito", "Usuario actualizado correctamente!");

            Usuario logueado = (Usuario) session.getAttribute("usuariosession");

            if (logueado.getRol().toString().equals("ADMIN")) {
                session.setAttribute("usuariosession", usuarioactualizado);
                return "redirect:/admin/dashboard";
            } else {
                session.setAttribute("usuariosession", usuarioactualizado);
                return "redirect:/perfil/" + id;
            }
        } catch (Exception ex) {

            modelo.put("error", ex.getMessage());
            //modelo.put("nombre", nombre);
            modelo.put("email", email);
            modelo.put("password", password);

            return "modificar_cliente.html";
        }

    }

//eliminar foto funcion btn
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_CLIENTE' , 'ROLE_PROVEEDOR', 'ROLE_ADMIN')")
    @GetMapping("/perfils/emilinar-foto/{id}")
    public String eliminarFoto(@PathVariable String id, HttpSession session, MultipartFile archivo, RedirectAttributes redirectAttributes) {
        try {
            Usuario usuarioActual = (Usuario) session.getAttribute("usuariosession");
            System.out.println(usuarioActual.getId());
            System.out.println(id);
            if (usuarioActual != null && usuarioActual.getId().equals(id)) {
                Usuario usuarioActualizado = usuarioServicio.eliminarImagenDeUsuario(id);
                session.setAttribute("usuariosession", usuarioActualizado);
                return "redirect:/perfil/" + id;

            } else {
                System.out.println("estamos en else");
                throw new Exception("No tienes permisos");

            }
        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("msj", e.getMessage());
            System.out.println(" estamos en catch: " + e.getMessage());
            return "redirect:/error";
        }
    }

    //alta-baja usuario
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_CLIENTE' , 'ROLE_PROVEEDOR', 'ROLE_ADMIN')")
    @GetMapping("/perfils/modificar-alta/{id}")
    public String cambiarAltaUser(@PathVariable String id, HttpSession session) {

        System.out.println("CAMBIANDO ALTA-------");

        Usuario usuarioactualizado = usuarioServicio.cambiarAlta(id);

        session.setAttribute("usuariosession", usuarioactualizado);

        System.out.println("");
        System.out.println(usuarioactualizado.getActivo());
        System.out.println("controlador");
        return "redirect:/perfil/" + id;
    }

    /////////////////////////////////////////////////////////////////////////////////
    @Autowired
    private ComentarioServicio comentarioServicio;

    @Autowired
    private ServicioServicio servicioServicio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/registrar-proveedor")
    public String registrarProveedor() {

        return "usuario_registro.html";
    }

    @PostMapping("/registro2")
    public String registrarProveedor(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String telefono,
            @RequestParam Categoria categoria, @RequestParam String email, @RequestParam String password,
            @RequestParam String password2, ModelMap modelo, @RequestParam(required = false) MultipartFile archivo) throws MiException {
        try {
            usuarioServicio.crearProveedor(nombre, apellido, telefono, email, password, password2, categoria, archivo);
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
        List<Usuario> proveedores = usuarioServicio.listarProveedores();
        List<Usuario> electricidad = usuarioServicio.listarElectricidad();
        List<Usuario> plomeria = usuarioServicio.listarPlomeria();
        List<Usuario> salud = usuarioServicio.listarSalud();
        List<Usuario> limpieza = usuarioServicio.listarLimpieza();
        List<Usuario> jardineria = usuarioServicio.listarJardineria();
        List<Usuario> varios = usuarioServicio.listarVarios();

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

        modelo.put("proveedor", usuarioServicio.getOne(id));

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
            return "redirect:/servicios/proveedor/" + idProveedor;
        } catch (Excepciones ex) {
            System.out.println("1----------- EXCEPCION DE CARGA DE COMENTARIO");
            Logger.getLogger(IndexControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "servicio_pruebas.html";
    }


    @GetMapping("registro-servicio/{id}")
    public String registroServicio(ModelMap modelo) {

        modelo.addAttribute("categorias", Categoria.values());

        return "registrar_servicio_form.html";
    }

    @PostMapping("/registrar-servicio/{id}")
    public String crearServicio(@PathVariable String id, @RequestParam String descripcionServicio,
            @RequestParam Integer precioServicio) {

        try {
            servicioServicio.crearServicio(id, descripcionServicio, precioServicio);
            System.out.println("CONTROLADOR PROVEEDOR--- SERVICIO CARGADO---POST");
            return "redirect:/perfil/" + id;
        } catch (Excepciones e) {
            System.out.println("CATCH ERROR EN POST SERVICIO CREAR SERVICIO");
            return "index.html";
        }

    }

    @GetMapping("/servicios-contratados/{id}")
    public String serviciosContratados(@PathVariable String id, HttpSession session, ModelMap modelo) {
        usuario = (Usuario) session.getAttribute("usuariosession");
        modelo.addAttribute("usuario", usuario);

         if (usuario.getRol() == Rol.CLIENTE) {
            // TRAE CONTRATOS CLIENTE
            List<Contrato> contratosCliente = contratoServicio.findContratosByCliente(usuario);
            modelo.addAttribute("contratos", contratosCliente);
        } else if (usuario.getRol() == Rol.PROVEEDOR) {
            // TRAE CONTRATOS PROVEEDOR
            List<Contrato> contratosProveedor = contratoServicio.findContratosByProveedor(usuario);
            modelo.addAttribute("contratos", contratosProveedor);
        }

        return "servicios_contratados_lista.html";
    }

}
