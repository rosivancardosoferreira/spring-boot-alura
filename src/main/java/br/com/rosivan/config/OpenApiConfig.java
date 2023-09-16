package br.com.rosivan.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
        @Bean
        OpenAPI customOpenAPI() {
            return new OpenAPI()
                    .info(new Info()
                            .title("Rosivan api")
                            .version("v1")
                            .description("minha api de testo")
                            .termsOfService("termo de uso da api")
                            .license(new License()
                                    .name("Apache 2.0")
                                    .url("")
                            )
                    );
        }
}
