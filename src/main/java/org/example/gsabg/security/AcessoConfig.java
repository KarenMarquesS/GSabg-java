package org.example.gsabg.security;


import org.example.gsabg.model.Usuario;
import org.example.gsabg.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class AcessoConfig {

    @Autowired
    private UsuarioRepositorio userR;

    @Bean
    UserDetailsService acessoUser() {

      return email -> {
          Usuario usuario = userR.buscarPorEmail(email)
                  .orElseThrow(() -> new UsernameNotFoundException(">> E-mail não cadastrado <<"));

          return User.builder().username(usuario.getEmail())
                  .password(usuario.getSenha())
                  .roles("USER")
                  .build();
      };
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}


