package org.example.gsabg.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    OpenAPI configurarSwagger() {

        return new OpenAPI().info(new Info()
                .title("O AbrigoSmart - Assitente Virtual de Gestão de Abrigo temporário")
                .description("Esta aplicação tem por objetivo otimizar o processo de triagem de vítmas de desastres naturais")
                .version("v1.0.0")
                .license(new License().url("www.fiap.com.br")
                        .name("Licença - Projeto de Gestão Abrigo Temporário - v1.0.0"))
                .termsOfService("Termos de Serviço"));
    }
}
