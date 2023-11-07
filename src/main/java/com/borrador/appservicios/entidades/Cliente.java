<<<<<<< HEAD

package com.borrador.appservicios.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borrador.appservicios.entidades;

import javax.persistence.Entity;
>>>>>>> 8a74cd5bd50f82952c07633e3955e4e293750b74
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

<<<<<<< HEAD
=======
/**
 *
 * @author facun
 */
>>>>>>> 8a74cd5bd50f82952c07633e3955e4e293750b74
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cliente extends Usuario{
<<<<<<< HEAD
  
    private String nombre;
    private String apellido;
//    private String telefono;
=======
    
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    
    
>>>>>>> 8a74cd5bd50f82952c07633e3955e4e293750b74
    
}
