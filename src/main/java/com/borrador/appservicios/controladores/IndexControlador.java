package com.borrador.appservicios.controladores;

import com.borrador.appservicios.entidades.Proveedor;
import com.borrador.appservicios.entidades.Usuario;
import com.borrador.appservicios.enumeradores.Rol;

import com.borrador.appservicios.excepciones.Excepciones;
import com.borrador.appservicios.servicios.ProveedorServicio;
import com.borrador.appservicios.servicios.UsuarioServicio;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @GetMapping("/")
    public String Index() {
        return "index.html";
    }

    @GetMapping("/registrar-usuario")
    public String registrarUsuario(HttpSession session) {
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        if (logueado != null) {
            return "redirect:/";
        } else {
            return "usuario_registro.html";
        }
    }

    @PostMapping("/registro")
    public String registrarUsuario(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String email,
            @RequestParam String password, @RequestParam String password2, ModelMap modelo, @RequestParam(required = false) MultipartFile archivo) {

        try {
            usuarioServicio.persistirUsuario(nombre, apellido, email, password, password2, archivo);
            modelo.put("exito", "usuario registrado correctamente");
            return "redirect:/";

        } catch (Excepciones ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
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

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_PROVEEDOR', 'ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session, ModelMap modelo) {

        Usuario logueado = (Usuario) session.getAttribute("usuariosession");

        if (logueado.getRol().toString().equals("ADMIN")) {
            return "redirect:/admin/dashboard";
        }

        return "redirect:/";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_PROVEEDOR', 'ROLE_ADMIN')")
    @GetMapping("/perfil")
    public String perfilUsuario() {

        return "perfiles.html";
    }

    @GetMapping("/proveedor-registro/{id}")
    public String proveedorFormulario() {

        return "proveedor_registro.html";
    }

    @GetMapping("/servicios")
    public String servi() {

        return "servicios.html";
    }

    //ver perfil
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_PROVEEDOR', 'ROLE_ADMIN')")
    @GetMapping("/perfils")
    public String perfil(ModelMap modelo, HttpSession session) {
        usuario = (Usuario) session.getAttribute("usuariosession");
        modelo.put("usuario", usuario);
        return "modificar_cliente.html";
    }

    ///funcion actualizar datos de perfil
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_PROVEEDOR', 'ROLE_ADMIN')")
    @PostMapping("/perfils/{id}")
    public String actualizar(MultipartFile archivo, @PathVariable String id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String email,
            @RequestParam String password, @RequestParam String password2, ModelMap modelo, HttpSession session) {

        try {
            Usuario usuarioactualizado = usuarioServicio.actualizar(archivo, id, nombre, apellido, email, password, password2);

            modelo.put("exito", "Usuario actualizado correctamente!");

            Usuario logueado = (Usuario) session.getAttribute("usuariosession");

            if (logueado.getRol().toString().equals("ADMIN")) {
                session.setAttribute("usuariosession", usuarioactualizado);
                return "redirect:/admin/dashboard";
            } else {
                session.setAttribute("usuariosession", usuarioactualizado);
                return "redirect:/perfil";
            }
        } catch (Exception ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);
            modelo.put("password", password);

            return "modificar_cliente.html";
        }

    }

//eliminar foto funcion btn
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_PROVEEDOR', 'ROLE_ADMIN')")
    @GetMapping("/perfils/emilinar-foto/{id}")
    public String eliminarFoto(@PathVariable String id, HttpSession session, MultipartFile archivo, RedirectAttributes redirectAttributes) {
        try {
            Usuario usuarioActual = (Usuario) session.getAttribute("usuariosession");
            System.out.println(usuarioActual.getId());
            System.out.println(id);
            if (usuarioActual != null && usuarioActual.getId().equals(id)) {
                Usuario usuarioActualizado = usuarioServicio.eliminarImagenDeUsuario(id);
                session.setAttribute("usuariosession", usuarioActualizado);
                return "redirect:/perfil";

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
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_PROVEEDOR', 'ROLE_ADMIN')")
    @GetMapping("/perfils/modificar-alta/{id}")
    public String cambiarAltaUser(@PathVariable String id, HttpSession session) {

        System.out.println("CAMBIANDO ALTA-------");

        Usuario usuarioactualizado = usuarioServicio.cambiarAlta(id);

        session.setAttribute("usuariosession", usuarioactualizado);

        System.out.println("");
        System.out.println(usuarioactualizado.getActivo());
        System.out.println("controlador");
        return "redirect:/perfil";
    }

}
