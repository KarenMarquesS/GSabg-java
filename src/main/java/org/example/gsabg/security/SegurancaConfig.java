package org.example.gsabg.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SegurancaConfig {

    @Autowired
    private JWTAuthFilter jwtAuthFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    SecurityFilterChain filtrarRota(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request ->
                request.requestMatchers("/autenticacao/login","/swagger-ui/index.html","/usuario/{id_user}", "/usuario/{id_user}", "/usuario/paginado",
                                "/usuario/funcao", "/vitima/vitimaporidade", "/vitima/todasvitimas",
                                "/triagem/{id_triagem}", "/triagem/comInfo/{id_triagem}", "/triagem/classificacao",
                                "/triagem/prioridade", "/triagem/paginadas_estado", "/triagem/paginadas_data")
                        .permitAll().anyRequest().authenticated())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(servidor ->
                 servidor.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();

    }
}





