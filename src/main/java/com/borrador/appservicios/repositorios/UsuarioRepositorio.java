package com.borrador.appservicios.repositorios;

import com.borrador.appservicios.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 *
 * @author facun
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {

    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario buscarPorEmail(@Param("email") String email);

    @Modifying
    @Query("UPDATE Usuario u SET u.imagen = null WHERE u.id = :userId")
    void eliminarImagenDeUsuario(@Param("userId") String userId);

}
