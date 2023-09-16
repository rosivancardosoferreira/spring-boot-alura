package br.com.rosivan.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${cors.originPatterns:default}")
    private String corsOriginPatterns = "";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        var allowedOrigins = corsOriginPatterns.split(",");
        registry.addMapping("/**")
                // .allowedMethods("GET", "POST", "PUT") // PARA DEFINIR SOMENTE OS VERBOS QUE PODERAO ACESSAR
                .allowedMethods("*") // todos os verbos
                .allowedOrigins(allowedOrigins)
                .allowCredentials(true);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        http://localhost:8080,https://rosivancardoso.com.br,http://localhost:3000
        /*
        // VIA QUERY PARAMS
        configurer.favorParameter(true) // aceita parametros
                .parameterName("mediaType").ignoreAcceptHeader(true) // nome do parametro e ignora headers
                .useRegisteredExtensionsOnly(false) // o cara nao falou pra que serve
                .defaultContentType(MediaType.APPLICATION_JSON)
                    .mediaType("json", MediaType.APPLICATION_JSON)
                    .mediaType("xml", MediaType.APPLICATION_XML);
        */
        // VIA HEADER PARAMS
        configurer.favorParameter(false) // aceita parametros
                .ignoreAcceptHeader(false) // nome do parametro e ignora headers
                .useRegisteredExtensionsOnly(false) // o cara nao falou pra que serve
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML);
    }
}
