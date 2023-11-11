/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.borrador.appservicios.repositorios;

import com.borrador.appservicios.entidades.Contrato;
<<<<<<< HEAD
=======
import com.borrador.appservicios.entidades.Usuario;
import java.util.List;
>>>>>>> c739c42b1cf8daeadef67f459ed2cd5ca750b457
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author facun
 */
@Repository
<<<<<<< HEAD
public interface ContratoRepositorio extends JpaRepository<Contrato, String>{
    
=======
public interface ContratoRepositorio extends JpaRepository<Contrato, String> {

   List<Contrato> findByCliente(Usuario cliente);

   List<Contrato> findByProveedor(Usuario proveedor);

>>>>>>> c739c42b1cf8daeadef67f459ed2cd5ca750b457
}
