package com.borrador.appservicios.controladores;

import com.borrador.appservicios.excepciones.Excepciones;
import com.borrador.appservicios.servicios.ProveedorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/registrar-proveedor")
    public String registrarProveedor() {

        return "usuario_registro.html";
    }

    @PostMapping("/registro2")
    public String registrarProveedor(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String email,
            @RequestParam String password, @RequestParam String password2, ModelMap modelo, @RequestParam(required = false) MultipartFile archivo) {
        try {
            proveedorServicio.crearProveedor(nombre, apellido, email, password, password2, archivo);
            modelo.put("exito", "proveedor registrado correctamente");
            return "redirect:/";

        } catch (Excepciones ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);
            return "redirect:/";
        }
    }
}
