/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borrador.appservicios.servicios;

import com.borrador.appservicios.entidades.Comentario;
import com.borrador.appservicios.entidades.Proveedor;
import com.borrador.appservicios.entidades.Usuario;
import com.borrador.appservicios.excepciones.Excepciones;
import com.borrador.appservicios.repositorios.ComentarioRepositorio;
import com.borrador.appservicios.repositorios.ProveedorRepositorio;
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

    @Autowired
    private ProveedorRepositorio proveedorRepositorio;
//
//    @Autowired
//    private noticiaRepositorio noticiaRepositorio;

    public Comentario crearComentario(String comentarioTexto, String idUsuario, String idProveedor) throws Excepciones {
        System.out.println("INICIO SERVICIO ------------- CREAR COMENTARIO ------------");
        validar(comentarioTexto, idUsuario, idProveedor);
        System.out.println("2------COMENTARIO VALIDADO");
        Comentario comentario = new Comentario();

        System.out.println("2------BUSCANDO USUARIO QUE ESTA COMENTANDO");
        Usuario usuario = usuarioServicio.getOne(idUsuario);
        System.out.println("2------USUARIO ENCONTRADO : " + usuario.getEmail().toString());

        comentario.setComentario(comentarioTexto);
        comentario.setUsuario(usuario);
        comentario.setFecha(new Date());
        //System.out.println("2------ NOTICIA CONFIGURADA : " + noticia.toString());

        System.out.println("2------ BUSCANDO PROVEEDOR PARA INSERTAR COMENTARIO");
        Optional<Proveedor> respuesta = proveedorRepositorio.findById(idProveedor);
        if (respuesta.isPresent()) {
            System.out.println("2------ PROVEEDOR ENCONTRADA");
            Proveedor proveedor = respuesta.get();
            System.out.println("2------ EL PROVEEDOR ES "+proveedor.getNombre().toString());
            System.out.println("2------ BUSCANDO LISTA DE COMENTARIOS DENTRO DE LA PROVEEDOR");
            proveedor.getComentarios().add(comentario);
            System.out.println("2------ LISTA DE COMENTARIOS DENTRO DE LA NOTICIA -- ACTUALIZADA --");
        }

        System.out.println("FIN SERVICIO ------------- CREAR COMENTARIO ------------");
        return comentario;
    }

    @Transactional
    public void persistirComentario(String comentarioTexto, String idUsuario, String idProveedor) throws Excepciones {
        System.out.println("INICIO SERVICIO ------------- PERSISTIR COMENTARIO ------------");
        validar(comentarioTexto, idUsuario, idProveedor);
        Comentario comentario = crearComentario(comentarioTexto, idUsuario, idProveedor);
        comentarioRepositorio.save(comentario);
        System.out.println("FIN SERVICIO ------------- PERSISTIR COMENTARIO ------------");
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