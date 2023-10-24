/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.borrador.appservicios;

import com.borrador.appservicios.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author facun
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeguridadWeb {
    
    @Autowired
    public UsuarioServicio UsuarioServicio;
    
    @Autowired  // encriptaion de contraseña?? 
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(UsuarioServicio)//recibe la contraseña desde el servicio
                .passwordEncoder(new BCryptPasswordEncoder());// devuelve la contraseña encriptada antes de persistirla
    }
    
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http
            .authorizeRequests()
                .requestMatchers("/admin/*").hasRole("ADMIN")
                //.requestMatchers("/admin/*").hasRole("PROVEEDOR")// solo admin
                .requestMatchers("/css/*","/js/*","/img/*","/**")
                .permitAll()
            .and().formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/logincheck")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/inicio")
                .permitAll()
            .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll()
            .and().csrf() 
                .disable();
                
                
                return http.build();
    }
   
    
}
