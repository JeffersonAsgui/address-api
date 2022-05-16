package dev.asgui.address.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApiApi() {
        return new OpenAPI().info(new Info()
                .title("REST API ADDRESS")
                .description("Get Address by sending CEP")
                .version("1.0"));
    }
}
