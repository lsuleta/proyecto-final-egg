<<<<<<< HEAD
<<<<<<< HEAD

package com.borrador.appservicios.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cliente extends Usuario{
  
    private String nombre;
    private String apellido;
//    private String telefono;
    
}
=======
=======
>>>>>>> 8a74cd5bd50f82952c07633e3955e4e293750b74
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borrador.appservicios.entidades;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author facun
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cliente extends Usuario{
    
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    
    
    
}
<<<<<<< HEAD
>>>>>>> c829a549dcb420e8c95e8b4882a7a0c2a396a16a
=======
>>>>>>> 8a74cd5bd50f82952c07633e3955e4e293750b74
