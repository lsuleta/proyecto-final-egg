/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borrador.appservicios.servicios;

<<<<<<< HEAD
import com.borrador.appservicios.entidades.Cliente;
import com.borrador.appservicios.entidades.Usuario;
import com.borrador.appservicios.enumeradores.Rol;
import com.borrador.appservicios.excepciones.Excepciones;
import com.borrador.appservicios.repositorios.ClienteRepositorio;
=======
import com.borrador.appservicios.entidades.Usuario;
import com.borrador.appservicios.enumeradores.Rol;
import com.borrador.appservicios.excepciones.Excepciones;
>>>>>>> c739c42b1cf8daeadef67f459ed2cd5ca750b457
import com.borrador.appservicios.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author facun
 */
@Service
public class ClienteServicio {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void crearCliente(String email, String nombre, String apellido,
            String direccion, String telefono) throws Excepciones {
        
        try {

            Usuario cliente = usuarioRepositorio.buscarPorEmail(email);
            
            cliente.setRol(Rol.CLIENTE);
            
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setDireccion(direccion);
            cliente.setTelefono(telefono);

            System.out.println("PERSISTIENDO CLIENTE NUEVO...");
            usuarioRepositorio.save(cliente);
        } catch (Exception e) {
            throw new Excepciones("Error - crear cliente servicio ");
        }

    }

}
