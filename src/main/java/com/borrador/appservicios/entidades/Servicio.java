/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borrador.appservicios.entidades;

import com.borrador.appservicios.enumeradores.Categoria;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;
<<<<<<< HEAD
=======
import lombok.NoArgsConstructor;
>>>>>>> c739c42b1cf8daeadef67f459ed2cd5ca750b457
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author facun
 */
@Entity
@Getter
@Setter
<<<<<<< HEAD
=======
@NoArgsConstructor
>>>>>>> c739c42b1cf8daeadef67f459ed2cd5ca750b457
@ToString
public class Servicio {
            
    @Id
    @GeneratedValue(generator = "uuid")// Generar id alfanumerico unico
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @ManyToOne
<<<<<<< HEAD
    private Proveedor proveedor;
=======
    private Usuario proveedor;
>>>>>>> c739c42b1cf8daeadef67f459ed2cd5ca750b457
    
    private String descripcionServicio;
   
    private Integer precioServicio;
    
    
    
    
}
