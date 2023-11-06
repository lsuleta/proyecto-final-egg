package com.borrador.appservicios.servicios;

import com.borrador.appservicios.Exception.MiException;
import com.borrador.appservicios.entidades.Imagen;
import com.borrador.appservicios.entidades.Proveedor;

import com.borrador.appservicios.enumeradores.Rol;
import com.borrador.appservicios.excepciones.Excepciones;
import com.borrador.appservicios.repositorios.ProveedorRepositorio;
import com.borrador.appservicios.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author facun
 */
@Service
public class ProveedorServicio implements UserDetailsService{

    Proveedor proveedor;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private ProveedorRepositorio proveedorRepositorio;

    @Autowired
    private ImagenServicio imagenServicio;

    @Transactional
    public void crearProveedor(
            String nombre, 
            String apellido, 
            String email,
            String password,
            String password2,
            MultipartFile archivo) throws Excepciones, MiException {
            
            validar(nombre, apellido, email, password, password2);
            
            proveedor = new Proveedor();

            proveedor.setNombre(nombre);
            proveedor.setApellido(apellido);
            proveedor.setEmail(email);
            proveedor.setPassword(new BCryptPasswordEncoder().encode(password));
            proveedor.setFechaDeAlta(new Date());
            
            cargarImagen(archivo);
            
            proveedor.setRol(Rol.PROVEEDOR);
            proveedor.setActivo(true);

            proveedor.setServicioTipo(null);
            proveedor.setServicioDescripcion(null);
            proveedor.setClientes(new ArrayList());
            proveedor.setCalificacion(0);

            proveedorRepositorio.save(proveedor);       
    }

    public boolean cargarImagen(MultipartFile archivo) throws Excepciones {    
        if (!archivo.isEmpty()) {
            Imagen imagen = imagenServicio.guardar(archivo);
            proveedor.setImagen(imagen);
            return true;
        } else //usuario.setImagen(null);
        {
            return false;
        }

    }
    
    public void validar(String nombre, String apellido, String email, String password, String password2) throws MiException, Excepciones {
        
        if (nombre.isEmpty() || nombre == null) {
            throw new Excepciones("El nombre no puede ser nulo o estar vacio");
        }
        
        if (apellido.isEmpty() || apellido == null) {
            throw new Excepciones("El apellido no puede ser nulo o estar vacio");
        }

        if (email.isEmpty() || email == null) {
            throw new Excepciones("El email no puede ser nulo o estar vacio");
        }
        
        if (password.isEmpty() || password == null) {
            throw new Excepciones("El password no puede ser nulo o estar vacio");
        }
        if (password2.isEmpty() || password2 == null) {
            throw new Excepciones("El password2 no puede ser nulo o estar vacio");
        }

    }
    
    public Proveedor getOne(String id) {
        return proveedorRepositorio.getOne(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        proveedor = proveedorRepositorio.buscarPorEmail(email);

        if (proveedor != null) {
            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + proveedor.getRol().toString()); //ROLE_PROVEEDOR 
            permisos.add(p);
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("proveedorsession", proveedor);
            return new User(proveedor.getEmail(), proveedor.getPassword(), permisos);
        } else {
            return null;
        }
    }   
}
