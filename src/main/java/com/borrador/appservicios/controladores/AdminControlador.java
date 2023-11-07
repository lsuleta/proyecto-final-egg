<<<<<<< HEAD
package com.borrador.appservicios.controladores;

import com.borrador.appservicios.entidades.Usuario;
import com.borrador.appservicios.servicios.AdminSevicio;
import com.borrador.appservicios.servicios.UsuarioServicio;
import java.util.List;
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

/**
 *
 * @author Kyouma
 */
@Controller
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private AdminSevicio adminSevicio;

    @GetMapping("/dashboard")
    public String panelAdministrativo() {
        return "panel.html";
    }

    @GetMapping("/usuarios")
    public String listar(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);
        return "usuario_list.html";
    }

    @GetMapping("/modificar/{id}")
    public String usuarioModificar(@PathVariable String id, ModelMap modelo) {

        modelo.put("usuario", usuarioServicio.getOne(id));

        return "adm_usr_modificar.html";
    }

    @GetMapping("/modificarRol/{id}")
    public String cambiarRol(@PathVariable String id) {
        adminSevicio.cambiarRol(id);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/Alta/{id}")
    public String cambiarAlta(@PathVariable String id) {
        adminSevicio.Alta(id);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/modificarRolUser/{id}")
    public String cambiarRolUser(@PathVariable String id) {
        adminSevicio.cambiarRolUser(id);
        return "redirect:/admin/usuarios";
    }
    
    @GetMapping("/modificarRolCliente/{id}")
    public String cambiarRolCliente(@PathVariable String id) {
        adminSevicio.cambiarRolCiente(id);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/modificarRolProveedor/{id}")
    public String cambiarRolProv(@PathVariable String id) {
        adminSevicio.cambiarRolProveedor(id);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/modificarRolModerador/{id}")
    public String cambiarRolMod(@PathVariable String id) {
        adminSevicio.cambiarRolMod(id);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/modificarRolAdministrador/{id}")
    public String cambiarRolAdmin(@PathVariable String id) {
        adminSevicio.cambiarRolAdmin(id);
        return "redirect:/admin/usuarios";
    }

    // ---------- Eliminar Usuario de la BD -----------//
    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarUsuarioBD(@PathVariable String id) {
        adminSevicio.eliminar(id);
        return "redirect:/admin/usuarios";
    }

    @PostMapping("/modificarUsuario/{id}")
    public String modificarUsuarioBD(@PathVariable String id,
            MultipartFile archivo,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String email,
            ModelMap modelo, HttpSession session) throws Exception {
        adminSevicio.actualizar(archivo, id, nombre, apellido, email);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/confirmarEliminarUsuario/{id}")
    public String eliminarUsuarioVista(@PathVariable String id, ModelMap modelo) {
        modelo.addAttribute("usuario", usuarioServicio.getOne(id));
        return "eliminar_confirmacion.html";
    }

}
=======
package com.borrador.appservicios.controladores;

import com.borrador.appservicios.entidades.Usuario;
import com.borrador.appservicios.servicios.AdminSevicio;
import com.borrador.appservicios.servicios.UsuarioServicio;
import java.util.List;
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

/**
 *
 * @author Kyouma
 */
@Controller
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private AdminSevicio adminSevicio;

    @GetMapping("/dashboard")
    public String panelAdministrativo() {
        return "panel.html";
    }

    @GetMapping("/usuarios")
    public String listar(ModelMap modelo) {
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);
        return "usuario_list.html";
    }

    @GetMapping("/modificar/{id}")
    public String usuarioModificar(@PathVariable String id, ModelMap modelo) {

        modelo.put("usuario", usuarioServicio.getOne(id));

        return "adm_usr_modificar.html";
    }

    @GetMapping("/modificarRol/{id}")
    public String cambiarRol(@PathVariable String id) {
        adminSevicio.cambiarRol(id);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/Alta/{id}")
    public String cambiarAlta(@PathVariable String id) {
        adminSevicio.Alta(id);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/modificarRolUser/{id}")
    public String cambiarRolUser(@PathVariable String id) {
        adminSevicio.cambiarRolUser(id);
        return "redirect:/admin/usuarios";
    }
    
    @GetMapping("/modificarRolCliente/{id}")
    public String cambiarRolCliente(@PathVariable String id) {
        adminSevicio.cambiarRolCiente(id);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/modificarRolProveedor/{id}")
    public String cambiarRolProv(@PathVariable String id) {
        adminSevicio.cambiarRolProveedor(id);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/modificarRolModerador/{id}")
    public String cambiarRolMod(@PathVariable String id) {
        adminSevicio.cambiarRolMod(id);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/modificarRolAdministrador/{id}")
    public String cambiarRolAdmin(@PathVariable String id) {
        adminSevicio.cambiarRolAdmin(id);
        return "redirect:/admin/usuarios";
    }

    // ---------- Eliminar Usuario de la BD -----------//
    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarUsuarioBD(@PathVariable String id) {
        adminSevicio.eliminar(id);
        return "redirect:/admin/usuarios";
    }

    @PostMapping("/modificarUsuario/{id}")
    public String modificarUsuarioBD(@PathVariable String id,
            MultipartFile archivo,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String email,
            ModelMap modelo, HttpSession session) throws Exception {
        adminSevicio.actualizar(archivo, id, nombre, apellido, email);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/confirmarEliminarUsuario/{id}")
    public String eliminarUsuarioVista(@PathVariable String id, ModelMap modelo) {
        modelo.addAttribute("usuario", usuarioServicio.getOne(id));
        return "eliminar_confirmacion.html";
    }

}
>>>>>>> 8a74cd5bd50f82952c07633e3955e4e293750b74
