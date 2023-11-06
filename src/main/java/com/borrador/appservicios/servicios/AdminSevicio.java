package com.borrador.appservicios.servicios;

import com.borrador.appservicios.entidades.Usuario;
import com.borrador.appservicios.repositorios.ImagenRepositorio;
import com.borrador.appservicios.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author Kyouma
 */
@Service
public class AdminSevicio implements UserDetailsService {

    Usuario usuario;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private ImagenRepositorio imagenRepositorio;

    @Autowired
    private ImagenServicio imagenServicio;

  public Usuario getOne(String id) {
        return usuarioRepositorio.getOne(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        usuario = usuarioRepositorio.buscarPorEmail(email);

        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString()); //ROLE_USER
            permisos.add(p);
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);
            return new User(usuario.getEmail(), usuario.getPassword(), permisos);
        } else {
            return null;
        }

    }
     @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList();
        usuarios = usuarioRepositorio.findAll();
        return usuarios;
    }

    @Transactional
    public Usuario Alta(String id) {
        Optional<Usuario> resp = usuarioRepositorio.findById(id);

        if (resp.isPresent()) {
            usuario = resp.get();
            boolean b = usuario.getActivo();
            usuario.setActivo(!b);

            System.out.println("");
            System.out.println(usuario.getActivo());
            System.out.println("Alta servicio");

            System.out.println("");
            System.out.println("");
            System.out.println("email: " + usuario.getEmail());

            System.out.println("");
            System.out.println("");

            return usuario;

        }

        return usuario;
    }

}
