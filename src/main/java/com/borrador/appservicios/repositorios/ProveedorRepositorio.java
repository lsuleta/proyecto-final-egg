package com.borrador.appservicios.repositorios;

import com.borrador.appservicios.entidades.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author facun
 */
@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor, String>{
    
}
