package br.com.rosivan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

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
