<<<<<<< HEAD
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borrador.appservicios.servicios;

import com.borrador.appservicios.entidades.Cliente;
import com.borrador.appservicios.entidades.Usuario;
import com.borrador.appservicios.enumeradores.Rol;
import com.borrador.appservicios.repositorios.ClienteRepositorio;
import com.borrador.appservicios.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author facun
 */

@Service
public class ClienteServicio {
    
    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    ClienteRepositorio clienteRepositorio;
    
    public void crearCliente(String email ,String nombre, String apellido,
            String direccion, String telefono){
        
        Cliente cliente = (Cliente) usuarioRepositorio.buscarPorEmail(email);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        cliente.setRol(Rol.CLIENTE);
        
        clienteRepositorio.save(cliente);
        
    }
    
    
    
}
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borrador.appservicios.servicios;

import com.borrador.appservicios.entidades.Cliente;
import com.borrador.appservicios.entidades.Usuario;
import com.borrador.appservicios.enumeradores.Rol;
import com.borrador.appservicios.repositorios.ClienteRepositorio;
import com.borrador.appservicios.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author facun
 */

@Service
public class ClienteServicio {
    
    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    ClienteRepositorio clienteRepositorio;
    
    public void crearCliente(String email ,String nombre, String apellido,
            String direccion, String telefono){
        
        Cliente cliente = (Cliente) usuarioRepositorio.buscarPorEmail(email);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        cliente.setRol(Rol.CLIENTE);
        
        clienteRepositorio.save(cliente);
        
    }
    
    
    
}
>>>>>>> 8a74cd5bd50f82952c07633e3955e4e293750b74
