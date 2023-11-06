package com.borrador.appservicios.controladores;

import com.borrador.appservicios.entidades.Usuario;
import com.borrador.appservicios.servicios.AdminSevicio;
import com.borrador.appservicios.servicios.UsuarioServicio;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        usuarioServicio.cambiarRol(id);
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
    
}
