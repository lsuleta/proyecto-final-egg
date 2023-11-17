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
public interface ContratoRepositorio extends JpaRepository<Contrato, String> {

    List<Contrato> findByCliente(Usuario cliente);

    List<Contrato> findByProveedor(Usuario proveedor);

    List<Contrato> findByClienteAndCliente_Rol(Usuario cliente, String rol);

    List<Contrato> findByClienteAndContratoIniciadoIsTrue(Usuario cliente);
    List<Contrato> findByClienteAndContratoCanceladoIsTrue(Usuario cliente);
    List<Contrato> findByClienteAndContratoFinalizadoIsTrue(Usuario cliente);



}
