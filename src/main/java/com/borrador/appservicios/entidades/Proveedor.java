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

/**
 *
 * @author Kyouma
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Proveedor extends Usuario {
    
    private String nombre;
    private String apellido;
    private String telefono;
    private Categoria categoriaServicio;
    
    @OneToMany
    private List<Comentario> comentarios;

    

}
