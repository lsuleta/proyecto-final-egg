///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.borrador.appservicios.entidades;
//
//import java.util.List;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.OneToOne;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//import org.hibernate.annotations.GenericGenerator;
//
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@ToString
//public class Calificacion {
//    
//    @Id
//    @GeneratedValue(generator = "uuid")// Generar id alfanumerico unico
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
//    private String id;
//        
//    private List<Double> calificacion;
//    
//    @OneToOne
//    private Contrato contrato;
//    
//}
