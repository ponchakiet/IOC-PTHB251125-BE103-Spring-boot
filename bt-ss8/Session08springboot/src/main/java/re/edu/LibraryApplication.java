package re.edu;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Value("${cloud-name}")
    private String cloudName;
    @Value("${api-key}")
    private String apiKey;
    @Value("${api-secret}")
    private String apiSecret;

    // Bean upload
    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name",cloudName,
                "api_key",apiKey,
                "api_secret",apiSecret,
                "secure", true
        ));
    }

}
