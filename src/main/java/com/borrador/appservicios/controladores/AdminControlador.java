package com.borrador.appservicios.controladores;

import com.borrador.appservicios.entidades.Usuario;
import com.borrador.appservicios.servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Kyouma
 */
@Controller
@RequestMapping("/admin")
public class AdminControlador {
    
        @Autowired
    private UsuarioServicio usuarioServicio;
    
    
    @GetMapping("/dashboard")
    public String panelAdministrativo(){
        return "panel.html";
    }
    
    @GetMapping("/usuarios")
    public String listar(ModelMap modelo){
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);
        return "usuario_list.html";
    }
    
    
      
    @GetMapping("/modificar/{id}")
    public String usuarioModificar(@PathVariable String id, ModelMap modelo){
        
        modelo.put("usuario", usuarioServicio.getOne(id));
        
        return "usuario_modificar.html";
    }
    
    @GetMapping("/modificarRol/{id}")
    public String cambiarRol(@PathVariable String id){
        usuarioServicio.cambiarRol(id);
        return "redirect:/admin/usuarios";
    }
    
    @GetMapping("/modificarAlta/{id}")
    public String cambiarAlta(@PathVariable String id){
        usuarioServicio.cambiarAlta(id);
        return "redirect:/admin/usuarios";
    }

}