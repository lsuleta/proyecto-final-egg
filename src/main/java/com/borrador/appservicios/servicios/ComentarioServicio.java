/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borrador.appservicios.servicios;

import com.borrador.appservicios.entidades.Comentario;
<<<<<<< HEAD
import com.borrador.appservicios.entidades.Proveedor;
import com.borrador.appservicios.entidades.Usuario;
import com.borrador.appservicios.excepciones.Excepciones;
import com.borrador.appservicios.repositorios.ComentarioRepositorio;
import com.borrador.appservicios.repositorios.ProveedorRepositorio;
=======
import com.borrador.appservicios.entidades.Usuario;
import com.borrador.appservicios.excepciones.Excepciones;
import com.borrador.appservicios.repositorios.ComentarioRepositorio;
>>>>>>> c739c42b1cf8daeadef67f459ed2cd5ca750b457
import com.borrador.appservicios.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author facun
 */
@Service
public class ComentarioServicio {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ComentarioRepositorio comentarioRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

<<<<<<< HEAD
    @Autowired
    private ProveedorRepositorio proveedorRepositorio;
=======
//    @Autowired
//    private ProveedorRepositorio proveedorRepositorio;
>>>>>>> c739c42b1cf8daeadef67f459ed2cd5ca750b457

    
    public Comentario crearComentario(String comentarioTexto, String idUsuario, String idProveedor) throws Excepciones {
       
        validar(comentarioTexto, idUsuario, idProveedor);
        Comentario comentario = new Comentario();

        Usuario usuario = usuarioServicio.getOne(idUsuario);

        comentario.setComentario(comentarioTexto);
        comentario.setUsuario(usuario);
        comentario.setFecha(new Date());

<<<<<<< HEAD
        Optional<Proveedor> respuesta = proveedorRepositorio.findById(idProveedor);
        if (respuesta.isPresent()) {
            Proveedor proveedor = respuesta.get();
=======
        Optional<Usuario> respuesta = usuarioRepositorio.findById(idProveedor);
        if (respuesta.isPresent()) {
            Usuario proveedor = respuesta.get();
>>>>>>> c739c42b1cf8daeadef67f459ed2cd5ca750b457
            proveedor.getComentarios().add(comentario);
        }

        return comentario;
    }

    @Transactional
    public void persistirComentario(String comentarioTexto, String idUsuario, String idProveedor) throws Excepciones {
        validar(comentarioTexto, idUsuario, idProveedor);
        Comentario comentario = crearComentario(comentarioTexto, idUsuario, idProveedor);
        comentarioRepositorio.save(comentario);
    }

    private void validar(String comentario, String idUsuario, String idProveedor) throws Excepciones {
        if (comentario.isEmpty() || comentario == null) {
            throw new Excepciones("el comentario no puede ser nulo o estar vacio");
        }
        if (idUsuario.isEmpty() || idUsuario == null) {
            throw new Excepciones("el idUsuario no puede ser nulo o estar vacio");
        }
        if (idUsuario.isEmpty() || idProveedor == null) {
            throw new Excepciones("el idProveedor no puede ser nulo o estar vacio");
        }

    }

    public List<Comentario> listarComentarios() {
        List<Comentario> comentarios = new ArrayList();
       
        comentarios = comentarioRepositorio.findAll();

        return comentarios;
    }

}