package ma.rest.spring.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI banqueOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestion des Comptes Bancaires")
                        .description("API REST pour gérer les comptes bancaires - TP8")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Équipe de développement")
                                .email("contact@banque.ma"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}

