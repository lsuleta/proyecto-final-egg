/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.borrador.appservicios.repositorios;

import com.borrador.appservicios.entidades.Contrato;
import com.borrador.appservicios.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author facun
 */
@Repository
public interface ContratoRepositorio extends JpaRepository<Contrato, String>{
    
    @Query("SELECT c FROM Contrato c WHERE c.cliente.id = :idCliente")
    List<Contrato> listarContratosCliente(@Param("idCliente") String idCliente);
    
    @Query("SELECT c FROM Contrato c WHERE c.proveedor = :idProveedor")
   // @Query("SELECT c FROM Contrato c WHERE c.proveedor.id = :idProveedor AND c.proveedor.rol = 'PROVEEDOR'")
    List<Contrato> listarContratosProveedor(@Param("idProveedor") String idProveedor);

    
    
    
    @Query("SELECT c FROM Contrato c WHERE c.cliente = :usuario")
    List<Contrato> listarContratos(@Param("usuario") Usuario usuario);

    
    
}
