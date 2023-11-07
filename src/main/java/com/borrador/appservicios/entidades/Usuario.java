<<<<<<< HEAD

package com.borrador.appservicios.entidades;

import com.borrador.appservicios.enumeradores.Genero;
=======
package com.borrador.appservicios.entidades;

>>>>>>> c829a549dcb420e8c95e8b4882a7a0c2a396a16a
import com.borrador.appservicios.enumeradores.Rol;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

<<<<<<< HEAD

=======
/**
 *
 * @author facun
 */
>>>>>>> c829a549dcb420e8c95e8b4882a7a0c2a396a16a
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Usuario {
    
    @Id
    @GeneratedValue(generator = "uuid")// Generar id alfanumerico unico
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @Column(name = "email",unique=true ,nullable = false)
    private String email;
    private String password;
<<<<<<< HEAD

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @Temporal(javax.persistence.TemporalType.DATE)
//    private Date fechaDeAlta;

    @Enumerated(EnumType.STRING)
    private Rol rol;
//    private Genero genero;

    private Boolean activo;
    private Integer intentos;
    private Date lastLogin;
=======
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ultimaConexion;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    private Boolean activo;
    
    private Integer intentos;
    
>>>>>>> c829a549dcb420e8c95e8b4882a7a0c2a396a16a

   // @OneToOne(cascade = CascadeType.PERSIST)
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "imagen_id", nullable = true)
    private Imagen imagen;


}
<<<<<<< HEAD

=======
>>>>>>> c829a549dcb420e8c95e8b4882a7a0c2a396a16a
