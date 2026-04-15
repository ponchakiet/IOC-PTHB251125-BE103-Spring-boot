package re.edu.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean // modelmapper
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    // cấu hình OPENAPI
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("test API Student")
                        .description("Use Swagger UI")
                        .contact(new Contact().name("Cường Le").email("cuong208@gmail.com"))
                        .version("1.0")
                );
    }
}
