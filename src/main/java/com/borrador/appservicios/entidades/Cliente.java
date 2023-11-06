
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
