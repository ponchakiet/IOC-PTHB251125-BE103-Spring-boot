package re.edu.department;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Session9DepartmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(Session9DepartmentApplication.class, args);
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
