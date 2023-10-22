package br.com.zancheta.changeservice.confg.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Shopping cart service")
                        .description("Service to manage the shopping cart")
                        .version("v0.0.1")
                        .contact(new Contact().email("sac@panvel.com.br"))
                );
    }
}
