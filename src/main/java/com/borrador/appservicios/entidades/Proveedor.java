 package com.borrador.appservicios.entidades;

import com.borrador.appservicios.enumeradores.Categoria;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Proveedor extends Usuario {
    
<<<<<<< HEAD
//    @Id
//    @GeneratedValue(generator = "uuid")// Generar id alfanumerico unico
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
//    private String id;
    
    private String nombre;
    private String apellido;
    private String categoria;
//    private String telefono;
    
    @OneToOne
    private Prestacion prestacion;
//    @OneToMany
//    private List<Turno> turnos;
   
=======
    private String nombre;
    private String apellido;
    private String telefono;
    private Categoria categoriaServicio;
    
    @OneToMany
    private List<Comentario> comentarios;

    

>>>>>>> 8a74cd5bd50f82952c07633e3955e4e293750b74
}
