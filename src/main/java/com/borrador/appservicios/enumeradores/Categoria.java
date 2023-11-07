<<<<<<< HEAD
<<<<<<< HEAD

package com.borrador.appservicios.enumeradores;

public enum Categoria {
    SALUD,
    PLOMERIA,
    ELECTRICIDAD,
    JARDINERIA,
    LIMPIEZA,
    VARIOS;
}
=======
=======
>>>>>>> 8a74cd5bd50f82952c07633e3955e4e293750b74
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.borrador.appservicios.enumeradores;

/**
 *
 * @author facun
 */
public enum Categoria {
    SALUD("Salud"),
    PLOMERIA("Plomeria"),
    ELECTRICIDAD("Electricidad"),
    LIMPIEZA("Limpieza"),
    JARDINERIA("Jardineria"),
    VARIOS("Varios");
    
   
    private final String nombre;

    Categoria(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
}
<<<<<<< HEAD
>>>>>>> c829a549dcb420e8c95e8b4882a7a0c2a396a16a
=======
>>>>>>> 8a74cd5bd50f82952c07633e3955e4e293750b74
