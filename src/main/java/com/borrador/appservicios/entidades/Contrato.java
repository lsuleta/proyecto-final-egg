/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borrador.appservicios.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
<<<<<<< HEAD
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import lombok.Getter;
=======
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.NoArgsConstructor;
>>>>>>> c739c42b1cf8daeadef67f459ed2cd5ca750b457
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

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
public class Contrato {
    
    @Id
    @GeneratedValue(generator = "uuid")// Generar id alfanumerico unico
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;
     
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFin;
    
<<<<<<< HEAD
    @OneToOne
    private Usuario cliente;
    
    @OneToOne
    private Proveedor proveedor;
=======
    @ManyToOne
    private Usuario cliente;
    
    @ManyToOne
    private Usuario proveedor;
    
    @OneToOne
    private Servicio servicio;
>>>>>>> c739c42b1cf8daeadef67f459ed2cd5ca750b457
    
    private Integer precio;
    
    private Boolean contratoIniciado;
    private Boolean contratoFinalizado;
    private Boolean contratoCancelado;
<<<<<<< HEAD
=======
    
    
>>>>>>> c739c42b1cf8daeadef67f459ed2cd5ca750b457
     
}
